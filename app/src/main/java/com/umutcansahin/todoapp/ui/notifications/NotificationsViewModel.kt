package com.umutcansahin.todoapp.ui.notifications


import androidx.lifecycle.*
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.use_case.GetAllToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getAllToDoUseCase: GetAllToDoUseCase,
) : ViewModel() {

    private val _entities = MutableLiveData<List<ToDoEntity>>()
    val entity: LiveData<List<ToDoEntity>> = _entities

    fun getAllToDo() {
        viewModelScope.launch() {
            getAllToDoUseCase().collectLatest() { entities ->
                _entities.value = entities
            }
        }
    }
}