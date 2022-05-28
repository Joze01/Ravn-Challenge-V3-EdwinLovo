package com.elovo.data

import com.elovo.apollo.GetAllPeopleQuery
import com.elovo.apollo.GetPersonQuery

val personApolloModel = GetPersonQuery.Person(
    id = "1",
    name = "Yoda",
    eyeColor = "Red",
    hairColor = "Black",
    skinColor = "White",
    birthYear = "1990",
    vehicleConnection = GetPersonQuery.VehicleConnection(
        listOf(
            GetPersonQuery.Vehicle(id = "1", name = "Tesla")
        )
    ),
    homeworld = GetPersonQuery.Homeworld(id = "1", name = "Titan"),
    species = GetPersonQuery.Species(id = "1", name = "Human")
)

val personApolloModel2 = GetAllPeopleQuery.Person(
    id = "1",
    name = "Yoda",
    homeworld = GetAllPeopleQuery.Homeworld(id = "1", name = "Titan"),
    species = GetAllPeopleQuery.Species(id = "1", name = "Human")
)

val pageInfoModel = GetAllPeopleQuery.PageInfo(
    hasPreviousPage = false,
    hasNextPage = true,
    endCursor = "1234",
    startCursor = null
)

val allApolloPeople = GetAllPeopleQuery.AllPeople(
    people = listOf(
        personApolloModel2,
        personApolloModel2.copy(id = "2", name = "Luke"),
        personApolloModel2.copy(id = "3", name = "Leia")
    ),
    pageInfo = pageInfoModel
)
