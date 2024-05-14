package com.example.eventmanagment.data.di

import android.content.Context
import com.example.eventmanagment.data.database.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EventDatabase{
        return EventDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: EventDatabase) =database.taskDao()
}