package com.example.eventmanagment.data.di

import android.content.Context
import com.example.eventmanagment.data.datastore.LanguageDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object datastoreModule {

    @Provides
    @Singleton
    fun provideDataStoreContext(
        @ApplicationContext context: Context
    )= LanguageDatastore(context=context)
}

