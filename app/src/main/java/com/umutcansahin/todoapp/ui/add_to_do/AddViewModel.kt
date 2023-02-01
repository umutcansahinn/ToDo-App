package com.umutcansahin.todoapp.ui.add_to_do

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel
import com.umutcansahin.todoapp.domain.use_case.AllUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel(){

    private val _singleToDo = MutableLiveData<ToDoUIModel>()
    val singleToDo: LiveData<ToDoUIModel> = _singleToDo

    fun insertOrUpdate(name: String,isInsert: Boolean,id: Int?,type: String?,date: Date) {
        viewModelScope.launch(Dispatchers.IO) {

            allUseCases.insertOrUpdateToDoUseCase(
                name = name,
                isInsert = isInsert,
                id = id,
                type = type,
                date = date,
                isDone = false
            )
        }
    }

    fun getById(id: Int?) {
        viewModelScope.launch {
            if (id != 0 && id != null) {
                _singleToDo.value = allUseCases.getSingleToDoUseCase.invoke(id = id)
            }
        }
    }
}