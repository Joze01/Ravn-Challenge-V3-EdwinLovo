package com.elovo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.elovo.data.datasource.PeopleRemote
import com.elovo.data.db.RavnDB
import com.elovo.data.repository.base.BaseRepository
import com.elovo.data.repository.paging.PersonRemoteMediator
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val peopleRemote: PeopleRemote,
    private val ravnDB: RavnDB
) : PersonRepository, BaseRepository() {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPeople(): Flow<PagingData<Person>> =
        Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = PersonRemoteMediator(
                peopleRemote = peopleRemote,
                ravnDB = ravnDB
            ),
            pagingSourceFactory = { ravnDB.personDao().getPeople() }
        ).flow.map { pageData ->
            pageData.map { it.mapToApiModel().mapToDomainModel() }
        }
}
