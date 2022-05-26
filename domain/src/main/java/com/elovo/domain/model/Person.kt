package com.elovo.domain.model

data class Person(
    val id: String,
    val name: String?,
    val eyeColor: String?,
    val hairColor: String?,
    val skinColor: String?,
    val birthYear: String?,
    val vehicleConnection: List<Vehicle?>?,
    val homeworld: Homeworld?,
    val species: Species?
)
