package com.umutcansahin.todoapp.di

import android.app.Application
import androidx.room.Room
import com.umutcansahin.todoapp.utils.Constants
import com.umutcansahin.todoapp.data.local.ToDoDatabase
import com.umutcansahin.todoapp.data.repository.ToDoRepositoryImpl
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(app: Application): ToDoDatabase {
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoRepository(db: ToDoDatabase,): ToDoRepository {
        return ToDoRepositoryImpl(db.toDoDao)
    }

}