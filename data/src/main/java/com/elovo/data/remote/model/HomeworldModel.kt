package com.elovo.data.remote.model

import com.elovo.data.mapper.DomainModelMapper
import com.elovo.domain.model.Homeworld

data class HomeworldModel(
    val id: String?,
    val name: String?
) : DomainModelMapper<Homeworld> {

    override fun mapToDomainModel() = Homeworld(
        id = id,
        name = name
    )
}
