package com.elovo.data.repository

import com.elovo.data.datasource.PeopleRemote
import com.elovo.data.db.RavnDB
import com.elovo.data.repository.base.BaseRepository
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onMapping
import com.elovo.domain.interactor.repository.PersonRepository
import com.elovo.domain.model.AllPeople
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val peopleRemote: PeopleRemote,
    private val ravnDB: RavnDB
) : PersonRepository, BaseRepository() {

    override suspend fun getAllPeople(endCursor: String): RavnResult<AllPeople> =
        peopleRemote.getAllPeople(pageSize = PAGE_SIZE, endCursor = endCursor)
            .onMapping { RavnResult.Success(it.mapToDomainModel()) }

    companion object {
        const val PAGE_SIZE = 5
    }
}
