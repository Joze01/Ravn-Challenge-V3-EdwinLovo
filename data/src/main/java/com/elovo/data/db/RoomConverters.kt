package com.elovo.data.db

import androidx.room.TypeConverter
import com.elovo.data.remote.model.HomeworldModel
import com.elovo.data.remote.model.SpeciesModel
import com.elovo.data.remote.model.VehicleConnectionModel
import com.elovo.data.remote.model.VehicleModel
import com.elovo.data.util.GsonHelper.convertToData
import com.elovo.data.util.GsonHelper.convertToListData
import com.elovo.data.util.GsonHelper.convertToString

class RoomConverters {

    @TypeConverter
    fun fromHomeworld(homeworldModel: HomeworldModel?): String? =
        homeworldModel?.let { convertToString(it) }

    @TypeConverter
    fun toHomeworld(homeworldModel: String?): HomeworldModel? =
        homeworldModel?.let { convertToData(it, HomeworldModel::class.java) }

    @TypeConverter
    fun fromSpeciesModel(speciesModel: SpeciesModel?): String? =
        speciesModel?.let { convertToString(it) }

    @TypeConverter
    fun toSpeciesModel(speciesModel: String?): SpeciesModel? =
        speciesModel?.let { convertToData(it, SpeciesModel::class.java) }

    @TypeConverter
    fun fromVehicles(vehicles: List<VehicleModel?>?): String? =
        vehicles?.let { convertToString(vehicles) }

    @TypeConverter
    fun toVehicles(vehicles: String?): List<VehicleModel?>? =
        vehicles?.let { convertToListData(it) }

    @TypeConverter
    fun fromVehicleConnection(vehicleConnectionModel: VehicleConnectionModel?): String? =
        vehicleConnectionModel?.let { convertToString(it) }

    @TypeConverter
    fun toVehicleConnection(vehicleConnection: String?): VehicleConnectionModel? =
        vehicleConnection?.let { convertToData(it, VehicleConnectionModel::class.java) }
}
