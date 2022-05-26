package com.elovo.domain.model

data class Person(
    val id: String = "",
    val name: String? = "",
    val eyeColor: String? = "",
    val hairColor: String? = "",
    val skinColor: String? = "",
    val birthYear: String? = "",
    val vehicleConnection: List<Vehicle?>? = null,
    val homeworld: Homeworld? = null,
    val species: Species? = null
)
