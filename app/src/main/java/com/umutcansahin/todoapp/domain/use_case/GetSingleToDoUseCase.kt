package com.umutcansahin.todoapp.domain.use_case

import com.umutcansahin.todoapp.domain.mapper.ToDoEntityMapper
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import javax.inject.Inject

class GetSingleToDoUseCase @Inject constructor(
    private val repository: ToDoRepository,
    private val mapper: ToDoEntityMapper
) {

    suspend operator fun invoke(id: Int) = repository.getToDoById(id = id).run {
        mapper.map(entity = this)
    }



}
