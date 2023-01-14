package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import javax.inject.Inject

class GetSingleToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Int): ToDoEntity {
        return repository.getToDoById(id = id)
    }
}
