package com.umutcansahin.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ToDoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract val toDoDao: ToDoDao
}