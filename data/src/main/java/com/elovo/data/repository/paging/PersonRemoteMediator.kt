package com.elovo.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.elovo.data.datasource.PeopleRemote
import com.elovo.data.db.RavnDB
import com.elovo.data.db.model.PersonEntity
import com.elovo.data.db.model.PersonRemoteKeys
import com.elovo.data.util.onError
import com.elovo.data.util.onSuccess

@OptIn(ExperimentalPagingApi::class)
class PersonRemoteMediator(
    private val peopleRemote: PeopleRemote,
    private val ravnDB: RavnDB
) : RemoteMediator<String, PersonEntity>() {

    private val personDao = ravnDB.personDao()
    private val personRemoteKeysDao = ravnDB.personRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<String, PersonEntity>
    ): MediatorResult {
        var mediatorResult: MediatorResult = MediatorResult.Success(false)

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage ?: ""
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevPage
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextPage
            }
        }

        val response = peopleRemote.getAllPeople(pageSize = 5, endCursor = currentPage)
        response.onSuccess { data ->
            mediatorResult = data?.people?.let { people ->
                val endOfPaginationReached = people.isEmpty()
                val nextPage = if (data.pageInfo.hasNextPage) data.pageInfo.endCursor else ""
                val prevPage = if (data.pageInfo.hasPreviousPage) data.pageInfo.startCursor else ""

                ravnDB.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        personDao.deletePeople()
                        personRemoteKeysDao.deleteAllPersonRemoteKeys()
                    }

                    people.map { person ->
                        person?.let {
                            personRemoteKeysDao.addPersonRemoteKeys(
                                PersonRemoteKeys(
                                    id = person.id,
                                    prevPage = prevPage ?: "",
                                    nextPage = nextPage ?: ""
                                )
                            )
                            personDao.insertPerson(it.mapToRoomEntity())
                        }
                    }
                }
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } ?: MediatorResult.Success(endOfPaginationReached = true)
        }
        response.onError {
            mediatorResult = MediatorResult.Error(Throwable(cause = it))
        }
        return mediatorResult
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<String, PersonEntity>
    ): PersonRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                personRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<String, PersonEntity>
    ): PersonRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { person ->
                personRemoteKeysDao.getRemoteKeys(id = person.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<String, PersonEntity>
    ): PersonRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { person ->
                personRemoteKeysDao.getRemoteKeys(id = person.id)
            }
    }
}
