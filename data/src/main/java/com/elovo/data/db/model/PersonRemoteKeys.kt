package com.elovo.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonRemoteKeys(
    @PrimaryKey
    val id: String,
    val prevPage: String,
    val nextPage: String
)
