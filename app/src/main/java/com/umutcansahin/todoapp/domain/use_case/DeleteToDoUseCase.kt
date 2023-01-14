package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke(toDoEntity: ToDoEntity){
        return repository.deleteToDo(toDoEntity = toDoEntity)
    }
}