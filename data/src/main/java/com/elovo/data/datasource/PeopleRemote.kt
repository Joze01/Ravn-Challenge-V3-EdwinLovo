package com.elovo.data.datasource

import com.elovo.data.remote.model.AllPeopleModel
import com.elovo.data.remote.model.PersonModel
import com.elovo.domain.common.RavnResult

interface PeopleRemote {
    suspend fun getAllPeople(
        pageSize: Int,
        endCursor: String
    ): RavnResult<AllPeopleModel>

    suspend fun getPerson(
        personId: String
    ): RavnResult<PersonModel>
}
