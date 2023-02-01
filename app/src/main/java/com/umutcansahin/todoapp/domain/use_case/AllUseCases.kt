package com.umutcansahin.todoapp.domain.use_case

data class AllUseCases(
    val deleteToDoUseCase: DeleteToDoUseCase,
    val getAllToDoUseCase: GetAllToDoUseCase,
    val getSingleToDoUseCase: GetSingleToDoUseCase,
    val getToDoByTypeUseCase: GetToDoByTypeUseCase,
    val insertOrUpdateToDoUseCase: InsertOrUpdateToDoUseCase
)