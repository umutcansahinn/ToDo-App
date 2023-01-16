package com.umutcansahin.todoapp.ui.chart


import androidx.lifecycle.*
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel
import com.umutcansahin.todoapp.domain.use_case.GetAllToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getAllToDoUseCase: GetAllToDoUseCase,
) : ViewModel() {

    private val _entities = MutableLiveData<List<ToDoUIModel>>()
    val entity: LiveData<List<ToDoUIModel>> = _entities

    fun getAllToDo() {
        viewModelScope.launch() {
            getAllToDoUseCase().collectLatest() { entities ->
                _entities.value = entities
            }
        }
    }
}