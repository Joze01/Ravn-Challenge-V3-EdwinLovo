package com.elovo.data.mapper

import com.elovo.apollo.GetAllPeopleQuery
import com.elovo.apollo.GetPersonQuery
import com.elovo.data.remote.model.AllPeopleModel
import com.elovo.data.remote.model.HomeworldModel
import com.elovo.data.remote.model.PageInfoModel
import com.elovo.data.remote.model.PersonModel
import com.elovo.data.remote.model.SpeciesModel
import com.elovo.data.remote.model.VehicleConnectionModel
import com.elovo.data.remote.model.VehicleModel

// Mappers for GetAllPeopleQuery models

fun GetAllPeopleQuery.PageInfo.mapToModel() = PageInfoModel(
    startCursor = startCursor,
    endCursor = endCursor,
    hasNextPage = hasNextPage,
    hasPreviousPage = hasPreviousPage
)

fun GetAllPeopleQuery.Homeworld.mapToModel() = HomeworldModel(
    id = id,
    name = name
)

fun GetAllPeopleQuery.Species.mapToModel() = SpeciesModel(
    id = id,
    name = name
)

fun GetAllPeopleQuery.Person.mapToModel() = PersonModel(
    id = id,
    name = name,
    homeworld = homeworld?.mapToModel(),
    species = species?.mapToModel(),
    eyeColor = null,
    hairColor = null,
    skinColor = null,
    birthYear = null,
    vehicleConnection = null
)

fun GetAllPeopleQuery.AllPeople.mapToModel() = AllPeopleModel(
    people = people?.map { it?.mapToModel() },
    pageInfo = pageInfo.mapToModel()
)

// Mappers for GetPersonQuery models

fun GetPersonQuery.Homeworld.mapToModel() = HomeworldModel(
    id = id,
    name = name
)

fun GetPersonQuery.Species.mapToModel() = SpeciesModel(
    id = id,
    name = name
)

fun GetPersonQuery.Vehicle.mapToModel() = VehicleModel(
    id = id,
    name = name
)

fun GetPersonQuery.VehicleConnection.mapToModel() = VehicleConnectionModel(
    vehicles = vehicles?.map { it?.mapToModel() }
)

fun GetPersonQuery.Person.mapToModel() = PersonModel(
    id = id,
    name = name,
    homeworld = homeworld?.mapToModel(),
    species = species?.mapToModel(),
    eyeColor = eyeColor,
    hairColor = hairColor,
    skinColor = skinColor,
    birthYear = birthYear,
    vehicleConnection = vehicleConnection?.mapToModel()
)
