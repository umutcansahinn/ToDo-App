package com.umutcansahin.todoapp.domain.uimodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class ToDoUIModel(
    val id: Int,
    val name: String,
    val isDone: Boolean,
    val type: String,
    val timestamp: Date
): Parcelable

