package com.umutcansahin.todoapp.domain.mapper

import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel

class ToDoEntityMapper {

    fun map(entity: ToDoEntity): ToDoUIModel {
        return entity.toUiModel()
    }

    private fun ToDoEntity.toUiModel() = ToDoUIModel(
        id = getId(),
        name = getName(),
        isDone = getIsDone() ,
        type = getType(),
        timestamp = getTimestamp()
    )

    private fun ToDoEntity.getId() = id

    private fun ToDoEntity.getName() = name

    private fun ToDoEntity.getIsDone() = isDone

    private fun ToDoEntity.getType() = type.orEmpty()

    private fun ToDoEntity.getTimestamp() = timestamp



}