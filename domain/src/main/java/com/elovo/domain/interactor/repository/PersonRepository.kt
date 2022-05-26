package com.elovo.domain.interactor.repository

import com.elovo.domain.common.RavnResult
import com.elovo.domain.model.AllPeople

interface PersonRepository {

    suspend fun getAllPeople(endCursor: String): RavnResult<AllPeople>
}
