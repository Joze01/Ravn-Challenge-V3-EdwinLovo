package com.elovo.data.datasource

import com.elovo.apollo.GetAllPeopleQuery
import com.elovo.apollo.GetPersonQuery
import com.elovo.data.allApolloPeople
import com.elovo.data.datasource.base.BaseRemote
import com.elovo.data.mapper.mapToModel
import com.elovo.data.personApolloModel
import com.elovo.data.remote.model.AllPeopleModel
import com.elovo.data.remote.model.PersonModel
import com.elovo.domain.common.DataSourceException
import com.elovo.domain.common.RavnException
import com.elovo.domain.common.RavnResult
import com.elovo.domain.common.onMapping

class FakePeopleRemoteImpl : PeopleRemote, BaseRemote() {

    override suspend fun getAllPeople(
        pageSize: Int,
        endCursor: String
    ): RavnResult<AllPeopleModel> {
        val result = RavnResult.Success(GetAllPeopleQuery.Data(allApolloPeople))
        return result.onMapping { data ->
            data.allPeople?.let { people ->
                RavnResult.Success(people.mapToModel())
            } ?: RavnResult.Error(DataSourceException.Unexpected(RavnException.NO_DATA))
        }
    }

    override suspend fun getPerson(personId: String): RavnResult<PersonModel> {
        val result = RavnResult.Success(GetPersonQuery.Data(personApolloModel))
        return result.onMapping { data ->
            data.person?.let { person ->
                RavnResult.Success(person.mapToModel())
            } ?: RavnResult.Error(DataSourceException.Unexpected(RavnException.NO_DATA))
        }
    }
}
