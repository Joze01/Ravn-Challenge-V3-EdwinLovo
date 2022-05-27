package com.elovo.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elovo.data.mapper.ApiModelMapper
import com.elovo.data.remote.model.HomeworldModel
import com.elovo.data.remote.model.PersonModel
import com.elovo.data.remote.model.SpeciesModel
import com.elovo.data.remote.model.VehicleConnectionModel
import com.elovo.data.util.GsonHelper

@Entity
data class PersonEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val eyeColor: String?,
    val hairColor: String?,
    val skinColor: String?,
    val birthYear: String?,
    val vehicleConnection: VehicleConnectionModel?,
    val homeworld: HomeworldModel?,
    val species: SpeciesModel?,
    var isFavorite: Boolean? = null
) : ApiModelMapper<PersonModel> {

    override fun mapToApiModel(): PersonModel = GsonHelper.parse(this)
}
