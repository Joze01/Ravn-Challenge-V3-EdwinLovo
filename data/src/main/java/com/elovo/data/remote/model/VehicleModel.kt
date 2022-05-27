package com.elovo.data.remote.model

import com.elovo.data.mapper.DomainModelMapper
import com.elovo.data.util.GsonHelper
import com.elovo.domain.model.Vehicle

data class VehicleModel(
    val id: String,
    val name: String?
) : DomainModelMapper<Vehicle> {

    override fun mapToDomainModel(): Vehicle = GsonHelper.parse(this)

    companion object {
        fun mapToModel(model: Vehicle?): VehicleModel =
            VehicleModel(
                id = model?.id ?: "",
                name = model?.name
            )
    }
}
