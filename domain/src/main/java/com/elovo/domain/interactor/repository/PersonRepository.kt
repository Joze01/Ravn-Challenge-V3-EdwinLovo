package com.elovo.domain.interactor.repository

import com.elovo.domain.common.RavnResult
import com.elovo.domain.model.AllPeople
import com.elovo.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    suspend fun getAllPeople(endCursor: String): RavnResult<AllPeople>

    suspend fun getPerson(personId: String): Flow<RavnResult<Person>>
}
