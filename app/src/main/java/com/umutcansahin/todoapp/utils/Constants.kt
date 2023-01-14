package com.umutcansahin.todoapp.utils

import java.text.SimpleDateFormat
import java.util.*


object Constants {
    const val DATABASE_NAME = "toDo_database"
    const val CURRENT_DATE_FORMAT = "dd MMM yyyy"
    const val TAG_DATE_PICKER = "Tag_Date_Picker"

}

fun Date.toFormat(dateFormat: String): String {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).format(this)
}