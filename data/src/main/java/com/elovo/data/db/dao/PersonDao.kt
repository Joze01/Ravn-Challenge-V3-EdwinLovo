package com.elovo.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.elovo.data.db.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: List<PersonEntity>): Array<Long>

    @Update
    suspend fun updatePerson(personEntity: PersonEntity)

    @Query("DELETE FROM PersonEntity")
    suspend fun deletePeople()

    @Query("SELECT * FROM PersonEntity where id = :id")
    fun getPerson(id: String): Flow<PersonEntity?>

    @Query("SELECT * FROM PersonEntity")
    fun getPeople(): PagingSource<Int, PersonEntity>
}
