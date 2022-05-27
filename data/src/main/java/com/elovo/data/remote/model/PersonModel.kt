package com.elovo.data.remote.model

import com.elovo.data.db.model.PersonEntity
import com.elovo.data.mapper.DomainModelMapper
import com.elovo.data.mapper.RoomMapper
import com.elovo.domain.model.Person

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
) : RoomMapper<PersonEntity>, DomainModelMapper<Person> {

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

    override fun mapToDomainModel() = Person(
        id = id,
        name = name,
        eyeColor = eyeColor,
        hairColor = hairColor,
        skinColor = skinColor,
        birthYear = birthYear,
        vehicleConnection = vehicleConnection?.vehicles?.map { it?.mapToDomainModel() },
        homeworld = homeworld?.mapToDomainModel(),
        species = species?.mapToDomainModel()
    )

    companion object {
        fun mapToModel(model: Person): PersonModel =
            PersonModel(
                id = model.id,
                name = model.name,
                eyeColor = model.eyeColor,
                hairColor = model.hairColor,
                skinColor = model.skinColor,
                birthYear = model.birthYear,
                vehicleConnection = VehicleConnectionModel(
                    vehicles = model.vehicleConnection?.map { VehicleModel.mapToModel(it) }
                ),
                homeworld = HomeworldModel.mapToModel(model.homeworld),
                species = SpeciesModel.mapToModel(model.species)
            )
    }
}
