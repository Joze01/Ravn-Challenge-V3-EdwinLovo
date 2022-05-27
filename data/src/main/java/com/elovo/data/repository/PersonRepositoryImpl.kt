package com.elovo.data.repository

import com.elovo.data.datasource.PeopleRemote
import com.elovo.data.db.dao.PersonDao
import com.elovo.data.remote.model.PersonModel
import com.elovo.data.repository.base.BaseRepository
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onMapping
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.AllPeople
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow
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
                val person = PersonModel.mapToModel(it).mapToRoomEntity()
                personDao.insertPerson(person)
            },
            dbGetAction = {
                personDao.getPerson(personId)?.mapToApiModel()
            }
        )

    companion object {
        const val PAGE_SIZE = 5
    }
}
