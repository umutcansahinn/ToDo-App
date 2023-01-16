package com.umutcansahin.todoapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel
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

    private val _uiState = MutableLiveData<List<ToDoUIModel>>()
    val uiState: LiveData<List<ToDoUIModel>> = _uiState


    fun getAllToDo() {
        viewModelScope.launch() {

            getAllToDoUseCase().collectLatest {
                _uiState.value = it

            }
        }
    }

    fun deleteToDo(toDoUIModel: ToDoUIModel) {
        viewModelScope.launch {
            deleteToDoUseCase.invoke(toDoUIModel = toDoUIModel)

        }
    }
}