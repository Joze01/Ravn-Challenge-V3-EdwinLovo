package com.elovo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elovo.data.db.model.PersonRemoteKeys

@Dao
interface PersonRemoteKeysDao {

    @Query("SELECT * FROM PersonRemoteKeys WHERE id = :id")
    suspend fun getRemoteKeys(id: String): PersonRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonRemoteKeys(personRemoteKeys: PersonRemoteKeys)

    @Query("DELETE FROM PersonRemoteKeys")
    suspend fun deleteAllPersonRemoteKeys()
}
