package com.umutcansahin.todoapp.data.repository

import com.umutcansahin.todoapp.data.local.ToDoDao
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(
    private val dao: ToDoDao
) : ToDoRepository {
    override  fun getAllToDoList(): Flow<List<ToDoEntity>> {
        return dao.getAllToDo()
    }

    override fun getToDoByType(type: String): Flow<List<ToDoEntity>> {
        return dao.getToDoByType(type = type)
    }

    override suspend fun insertToDo(toDo: ToDoEntity) {
        dao.insertToDo(toDo = toDo)
    }

    override suspend fun getToDoById(id: Int): ToDoEntity {
        return dao.getToDoById(id = id)
    }

    override suspend fun deleteToDo(toDoEntity: ToDoEntity) {
        dao.deleteToDo(toDo = toDoEntity)
    }

    override suspend fun updateToDo(toDo: ToDoEntity) {
        dao.updateToDo(toDo = toDo)
    }
}