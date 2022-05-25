package com.elovo.data.datasource

import com.elovo.data.util.RavnResult

interface PeopleRemote {
    suspend fun getAllPeople(
        pageSize: Int,
        endCursor: String
    ): RavnResult<List<Any>>

    suspend fun getPerson(
        personId: String
    ): RavnResult<Any>
}
