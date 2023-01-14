package com.umutcansahin.todoapp.domain.repository

import com.umutcansahin.todoapp.data.local.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun getAllToDoList(): Flow<List<ToDoEntity>>

    fun getToDoByType(type: String): Flow<List<ToDoEntity>>

    suspend fun insertToDo(toDo: ToDoEntity)

    suspend fun getToDoById(id: Int): ToDoEntity

    suspend fun deleteToDo(toDoEntity: ToDoEntity)

    suspend fun updateToDo(toDo: ToDoEntity)
}