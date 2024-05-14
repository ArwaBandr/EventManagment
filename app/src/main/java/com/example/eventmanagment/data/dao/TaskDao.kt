package com.example.eventmanagment.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskWithTagLists
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
    suspend fun addTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * From task_table")
    fun getAllTask(): Flow<List<Task>>

    @Upsert
    suspend fun upsertTag(tags: Tags)

    @Delete
    suspend fun deletTag(tags: Tags)

    @Query("SELECT * From tags_table")
     fun getAllTags(): Flow<List<Tags>>

    @Query("Select * From tags_table where tag_name =:tagName")
    fun getTagsWithTask(tagName: String): Flow<List<TaskWithTagLists>>
}