package com.elovo.data.remote.model

import com.elovo.data.db.model.PersonEntity
import com.elovo.data.mapper.RoomMapper

data class PersonModel(
    val id: String,
    val name: String?,
    val eyeColor: String?,
    val hairColor: String?,
    val skinColor: String?,
    val birthYear: String?,
    val vehicleConnection: VehicleConnectionModel?,
    val homeworld: HomeworldModel?,
    val species: SpeciesModel?
) : RoomMapper<PersonEntity> {

    override fun mapToRoomEntity() = PersonEntity(
        id = id,
        name = name,
        eyeColor = eyeColor,
        hairColor = hairColor,
        skinColor = skinColor,
        birthYear = birthYear,
        vehicleConnection = vehicleConnection,
        homeworld = homeworld,
        species = species
    )
}
