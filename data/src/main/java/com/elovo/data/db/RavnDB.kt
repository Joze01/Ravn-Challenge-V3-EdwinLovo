package com.elovo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elovo.data.db.dao.PersonDao
import com.elovo.data.db.dao.PersonRemoteKeysDao
import com.elovo.data.db.model.PersonEntity
import com.elovo.data.db.model.PersonRemoteKeys

@Database(
    entities = [
        PersonEntity::class,
        PersonRemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class RavnDB : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun personRemoteKeysDao(): PersonRemoteKeysDao

    companion object {
        const val DB_NAME = "ravn_db"
    }
}
