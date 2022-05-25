package com.elovo.data.datasource

import com.elovo.data.datasource.base.BaseRemote
import com.elovo.data.mapper.mapToModel
import com.elovo.data.remote.SwapiApi
import com.elovo.data.remote.model.AllPeopleModel
import com.elovo.data.remote.model.PersonModel
import com.elovo.data.util.RavnResult
import com.elovo.data.util.onMapping

class PeopleRemoteImpl(private val swapiApi: SwapiApi) : PeopleRemote, BaseRemote() {

    override suspend fun getAllPeople(
        pageSize: Int,
        endCursor: String
    ): RavnResult<AllPeopleModel?> {
        val result = fetchData {
            swapiApi.getAllPeople(
                pageSize = pageSize,
                endCursor = endCursor
            ).execute()
        }
        return result.onMapping { data ->
            RavnResult.Success(data?.allPeople?.mapToModel())
        }
    }

    override suspend fun getPerson(personId: String): RavnResult<PersonModel?> {
        val result = fetchData {
            swapiApi.getPerson(personId = personId).execute()
        }
        return result.onMapping { data ->
            RavnResult.Success(data?.person?.mapToModel())
        }
    }
}
