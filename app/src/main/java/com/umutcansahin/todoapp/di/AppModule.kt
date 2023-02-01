package com.umutcansahin.todoapp.di

import android.app.Application
import androidx.room.Room
import com.umutcansahin.todoapp.utils.Constants
import com.umutcansahin.todoapp.data.local.ToDoDatabase
import com.umutcansahin.todoapp.data.repository.ToDoRepositoryImpl
import com.umutcansahin.todoapp.domain.mapper.ToDoEntityMapper
import com.umutcansahin.todoapp.domain.repository.ToDoRepository
import com.umutcansahin.todoapp.domain.use_case.*
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
    fun provideToDoRepository(db: ToDoDatabase): ToDoRepository {
        return ToDoRepositoryImpl(db.toDoDao)
    }

    @Provides
    @Singleton
    fun provideToDoEntityMapper(): ToDoEntityMapper {
        return ToDoEntityMapper()
    }

    @Provides
    @Singleton
    fun provideAllUseCases(repository: ToDoRepository,mapper: ToDoEntityMapper): AllUseCases {
        return AllUseCases(
            deleteToDoUseCase = DeleteToDoUseCase(repository = repository),
            getAllToDoUseCase = GetAllToDoUseCase(repository = repository, mapper = mapper),
            getSingleToDoUseCase = GetSingleToDoUseCase(repository = repository, mapper = mapper),
            getToDoByTypeUseCase = GetToDoByTypeUseCase(repository = repository, mapper = mapper),
            insertOrUpdateToDoUseCase = InsertOrUpdateToDoUseCase(repository = repository)
        )
    }

}