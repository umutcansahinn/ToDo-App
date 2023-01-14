package com.umutcansahin.todoapp.ui.add_to_do

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.todoapp.data.local.ToDoEntity
import com.umutcansahin.todoapp.domain.use_case.GetSingleToDoUseCase
import com.umutcansahin.todoapp.domain.use_case.InsertOrUpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val insertOrUpdateToDoUseCase: InsertOrUpdateToDoUseCase,
    private val getSingleToDoUseCase: GetSingleToDoUseCase
) : ViewModel(){

    private val _singleToDo = MutableLiveData<ToDoEntity>()
    val singleToDo: LiveData<ToDoEntity> = _singleToDo

    fun insertOrUpdate(name: String,isInsert: Boolean,id: Int?,type: String?,date: Date) {
        viewModelScope.launch(Dispatchers.IO) {

            insertOrUpdateToDoUseCase(name = name, isInsert = isInsert, id = id, type = type,date = date)
        }
    }

    fun getById(id: Int?) {
        viewModelScope.launch {
            id?.let {
                _singleToDo.value = getSingleToDoUseCase.invoke(id = id)
            }
        }
    }
}