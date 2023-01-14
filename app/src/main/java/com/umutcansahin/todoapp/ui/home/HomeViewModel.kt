package com.umutcansahin.todoapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.use_case.DeleteToDoUseCase
import com.umutcansahin.todoapp.domain.use_case.GetAllToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllToDoUseCase: GetAllToDoUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<List<ToDoEntity>>()
    val uiState: LiveData<List<ToDoEntity>> = _uiState


    fun getAllToDo() {
        viewModelScope.launch() {

            getAllToDoUseCase().collectLatest {
                _uiState.value = it

            }
        }
    }

    fun deleteToDo(toDoEntity: ToDoEntity) {
        viewModelScope.launch {
            deleteToDoUseCase.invoke(toDoEntity = toDoEntity)

        }
    }
}