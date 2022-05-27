package com.elovo.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elovo.data.db.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(personEntity: PersonEntity)

    @Query("UPDATE PersonEntity SET isFavorite = :isFavorite WHERE id = :personId")
    fun updateFavoriteStatus(personId: String, isFavorite: Boolean)

    @Query("DELETE FROM PersonEntity")
    suspend fun deletePeople()

    @Query("SELECT * FROM PersonEntity where id = :id")
    fun getPerson(id: String): PersonEntity?

    @Query("SELECT * FROM PersonEntity where id = :id")
    fun getPersonWithFlow(id: String): Flow<PersonEntity?>

    @Query("SELECT * FROM PersonEntity")
    fun getPeople(): PagingSource<Int, PersonEntity>
}
