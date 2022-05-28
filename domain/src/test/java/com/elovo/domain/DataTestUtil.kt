package com.elovo.domain

import com.elovo.domain.model.AllPeople
import com.elovo.domain.model.Homeworld
import com.elovo.domain.model.PageInfo
import com.elovo.domain.model.Person
import com.elovo.domain.model.Species
import com.elovo.domain.model.Vehicle

val person = Person(
    id = "1",
    name = "Yoda",
    eyeColor = "Red",
    hairColor = "Black",
    skinColor = "White",
    birthYear = "1990",
    vehicleConnection = listOf(
        Vehicle(id = "1", name = "Tesla")
    ),
    homeworld = Homeworld(id = "1", name = "Titan"),
    species = Species(id = "1", name = "Human")
)

val people = listOf(
    person,
    person.copy(id = "2", name = "Luke"),
    person.copy(id = "2", name = "Leia")
)

val allPeople = AllPeople(
    people = people,
    pageInfo = PageInfo(
        startCursor = null,
        endCursor = "123",
        hasPreviousPage = false,
        hasNextPage = true
    )
)
