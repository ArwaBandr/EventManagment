package com.example.eventmanagment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eventmanagment.data.dao.TaskDao
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task

@Database (entities = [Task::class , Tags::class], version = 1 , exportSchema = false)
abstract class EventDatabase :RoomDatabase() {

    abstract fun taskDao():TaskDao

    companion object{
        @Volatile
        private var INSTANCE:EventDatabase? =null

        fun getDatabase(context: Context):EventDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE =instance
                instance
            }
        }
    }
}