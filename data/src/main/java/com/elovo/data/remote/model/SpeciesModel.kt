package com.elovo.data.remote.model

import com.elovo.data.mapper.DomainModelMapper
import com.elovo.domain.model.Species

data class SpeciesModel(
    val id: String?,
    val name: String?
) : DomainModelMapper<Species> {

    override fun mapToDomainModel() = Species(
        id = id,
        name = name
    )
}
