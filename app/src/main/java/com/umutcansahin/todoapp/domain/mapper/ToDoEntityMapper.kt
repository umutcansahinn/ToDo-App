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
        timestamp = getTimestamp(),
        typeColor = getTypeColor()
    )

    private fun ToDoEntity.getId() = id

    private fun ToDoEntity.getName() = name

    private fun ToDoEntity.getIsDone() = isDone

    private fun ToDoEntity.getType() = type.orEmpty()

    private fun ToDoEntity.getTimestamp() = timestamp

    private fun ToDoEntity.getTypeColor() = when(type) {
        "School" -> "#FFFFAB91"
        "Sport" -> "#FFFFCC80"
        "Business" -> "#FFA5D6A7"
        "Shopping" -> "#FF80DEEA"
        else -> "#FFE4E4E4"
    }


}