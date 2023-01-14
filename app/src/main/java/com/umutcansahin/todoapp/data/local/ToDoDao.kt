package com.umutcansahin.todoapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT *  FROM todo ORDER BY id DESC")
    fun getAllToDo(): Flow<List<ToDoEntity>>

    @Query("SELECT *  FROM todo WHERE type = :type")
    fun getToDoByType(type: String): Flow<List<ToDoEntity>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getToDoById(id: Int): ToDoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDoEntity)

    @Delete
    suspend fun deleteToDo(toDo: ToDoEntity)

    @Update
    suspend fun updateToDo(toDo: ToDoEntity)
}