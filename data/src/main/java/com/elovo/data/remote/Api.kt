package com.elovo.data.remote

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.elovo.apollo.GetAllPeopleQuery
import com.elovo.apollo.GetPersonQuery

internal class Api(
    private val apolloClient: ApolloClient
) {

    fun getAllPeople(pageSize: Int, endCursor: String): ApolloCall<GetAllPeopleQuery.Data> =
        apolloClient.query(GetAllPeopleQuery(pageSize, endCursor))

    fun getPerson(personId: String): ApolloCall<GetPersonQuery.Data> =
        apolloClient.query(GetPersonQuery(personId))
}
