package com.elovo.data.mapper

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

interface ApiModelMapper<out T : Any> {
    fun mapToApiModel(): T
}

interface DomainModelMapper<out T : Any> {
    fun mapToDomainModel(): T
}
