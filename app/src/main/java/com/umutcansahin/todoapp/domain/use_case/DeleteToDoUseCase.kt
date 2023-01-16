package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val repository: ToDoRepository
) {

    suspend operator fun invoke(toDoUIModel: ToDoUIModel) {

        return repository.deleteToDo(
            toDoEntity = ToDoEntity(
                id = toDoUIModel.id,
                name = toDoUIModel.name,
                type = toDoUIModel.type,
                isDone = toDoUIModel.isDone,
                timestamp = toDoUIModel.timestamp
            )
        )
    }
}