package com.elovo.data.remote.model

import com.elovo.data.util.GsonHelper
import com.elovo.data.util.mapper.DomainModelMapper
import com.elovo.domain.model.Species

data class SpeciesModel(
    val id: String?,
    val name: String?
) : DomainModelMapper<Species> {

    override fun mapToDomainModel(): Species = GsonHelper.parse(this)

    companion object {
        fun mapToModel(model: Species?): SpeciesModel =
            SpeciesModel(
                id = model?.id,
                name = model?.name
            )
    }
}
