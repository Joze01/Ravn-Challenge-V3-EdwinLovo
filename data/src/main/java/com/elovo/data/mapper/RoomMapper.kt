package com.elovo.data.mapper

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}
