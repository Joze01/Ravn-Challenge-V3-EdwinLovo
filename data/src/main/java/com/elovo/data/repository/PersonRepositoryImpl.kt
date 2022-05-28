package com.elovo.data.repository

import com.elovo.data.db.dao.PersonDao
import com.elovo.data.remote.datasource.PeopleRemote
import com.elovo.data.remote.model.PersonModel
import com.elovo.data.repository.base.BaseRepository
import com.elovo.domain.common.DataSourceException
import com.elovo.domain.common.RavnException
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onMapping
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.AllPeople
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val peopleRemote: PeopleRemote,
    private val personDao: PersonDao
) : PersonRepository, BaseRepository() {

    override suspend fun getAllPeople(endCursor: String): RavnResult<AllPeople> =
        peopleRemote.getAllPeople(pageSize = PAGE_SIZE, endCursor = endCursor)
            .onMapping { RavnResult.Success(it.mapToDomainModel()) }

    override suspend fun getPerson(personId: String): Flow<RavnResult<Person>> =
        fetchData(
            apiDataProvider = peopleRemote.getPerson(personId).onMapping { personModel ->
                RavnResult.Success(personModel.mapToDomainModel())
            },
            dbSaveAction = {
                val currentPersonData = personDao.getPerson(personId)
                val newPersonData = PersonModel.mapToModel(it).mapToRoomEntity().apply {
                    isFavorite = currentPersonData?.isFavorite
                }
                personDao.insertPerson(newPersonData)
            },
            dbGetAction = {
                personDao.getPerson(personId)?.mapToApiModel()
            }
        )

    override fun updateFavoriteStatus(personId: String, isFavorite: Boolean): Flow<RavnResult<Person>> {
        personDao.updateFavoriteStatus(personId = personId, isFavorite = isFavorite)
        return personDao.getPersonWithFlow(personId).map { person ->
            person?.let {
                RavnResult.Success(it.mapToApiModel().mapToDomainModel())
            } ?: RavnResult.Error(DataSourceException.Unexpected(RavnException.UNKNOWN_ERROR))
        }
    }

    override fun getAllFavorites(): Flow<RavnResult<List<Person>>> =
        personDao.getFavorites().map { people ->
            RavnResult.Success(people.map { it.mapToApiModel().mapToDomainModel() })
        }

    companion object {
        const val PAGE_SIZE = 5
    }
}
