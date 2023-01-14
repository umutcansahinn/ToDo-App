package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToDoByTypeUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    operator fun invoke(type: String): Flow<List<ToDoEntity>> {
        return repository.getToDoByType(type = type)
    }
}
