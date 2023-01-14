package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    operator fun invoke(): Flow<List<ToDoEntity>> {
        return repository.getAllToDoList()
    }
}