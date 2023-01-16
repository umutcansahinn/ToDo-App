package com.umutcansahin.todoapp.ui.detail_pie_chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.todoapp.domain.uimodel.ToDoUIModel
import com.umutcansahin.todoapp.domain.use_case.GetToDoByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailChartViewModel @Inject constructor(
    private val getToDoByTypeUseCase: GetToDoByTypeUseCase
) : ViewModel (){

    private val _uiState = MutableLiveData<List<ToDoUIModel>>()
    val uiState: LiveData<List<ToDoUIModel>> = _uiState

    fun getToDoByType(type: String) {
        viewModelScope.launch() {

            getToDoByTypeUseCase(type = type).collectLatest {
                _uiState.value = it
            }
        }
    }
}