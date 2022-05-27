package com.elovo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elovo.data.db.dao.PersonDao
import com.elovo.data.db.model.PersonEntity

@Database(
    entities = [
        PersonEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class RavnDB : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        const val DB_NAME = "ravn_db"
    }
}
