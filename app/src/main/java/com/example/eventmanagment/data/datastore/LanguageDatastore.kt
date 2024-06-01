package com.example.eventmanagment.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val DATASTORE = "DATASTORE"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)

const val PREFERANCE_KEY= "language_key"


class LanguageDatastore @Inject constructor(context: Context){
    val Language_Preferences_Key = booleanPreferencesKey(PREFERANCE_KEY)
    private val dataStore = context.dataStore

    suspend fun changeLanguage(changed:Boolean){
        dataStore.edit{
            it[Language_Preferences_Key] =changed
        }
    }

     fun getLangugeSetting():Flow<Boolean>{
        return dataStore.data.map {
            it[Language_Preferences_Key]?:false
        }
    }
}