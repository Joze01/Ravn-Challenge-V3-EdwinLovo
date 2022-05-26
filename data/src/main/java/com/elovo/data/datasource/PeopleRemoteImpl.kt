package com.elovo.data.datasource

import com.elovo.data.datasource.base.BaseRemote
import com.elovo.data.mapper.mapToModel
import com.elovo.data.remote.SwapiApi
import com.elovo.data.remote.model.AllPeopleModel
import com.elovo.data.remote.model.PersonModel
import com.elovo.domain.common.DataSourceException
import com.elovo.domain.common.RavnException.NO_DATA
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onMapping

class PeopleRemoteImpl(private val swapiApi: SwapiApi) : PeopleRemote, BaseRemote() {

    override suspend fun getAllPeople(
        pageSize: Int,
        endCursor: String
    ): RavnResult<AllPeopleModel> {
        val result = fetchData {
            swapiApi.getAllPeople(
                pageSize = pageSize,
                endCursor = endCursor
            ).execute()
        }
        return result.onMapping { data ->
            data?.allPeople?.let { people ->
                RavnResult.Success(people.mapToModel())
            } ?: RavnResult.Error(DataSourceException.Unexpected(NO_DATA))
        }
    }

    override suspend fun getPerson(personId: String): RavnResult<PersonModel> {
        val result = fetchData {
            swapiApi.getPerson(personId = personId).execute()
        }
        return result.onMapping { data ->
            data?.person?.let { person ->
                RavnResult.Success(person.mapToModel())
            } ?: RavnResult.Error(DataSourceException.Unexpected(NO_DATA))
        }
    }
}
