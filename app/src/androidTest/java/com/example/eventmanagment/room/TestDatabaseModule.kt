package com.example.eventmanagment.room

import android.content.Context
import androidx.room.Room
import com.example.eventmanagment.data.database.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {
    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context)=
        Room.inMemoryDatabaseBuilder(
        context ,EventDatabase::class.java).allowMainThreadQueries().build()
}