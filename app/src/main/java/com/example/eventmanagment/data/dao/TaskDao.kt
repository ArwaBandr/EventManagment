package com.example.eventmanagment.data.dao

import android.app.appsearch.SearchResults
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.eventmanagment.data.entity.SearchedResult
import com.example.eventmanagment.data.entity.TagWithTaskLists
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskTagCrossRef
import com.example.eventmanagment.data.entity.TaskWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Transaction
    @Upsert
    suspend fun addTask(task: Task): Long

    @Transaction
    @Upsert
    suspend fun insertTaskWithTags(task: Task, tags: List<Tags>)

    @Transaction
    @Upsert
    suspend fun insertTaskTagCross(taskTagCrossRef: List<TaskTagCrossRef>)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * From task_table")
    fun getAllTask(): Flow<List<Task>>

    @Upsert
    suspend fun upsertTag(tags: Tags)

    @Delete
    suspend fun deletTag(tags: Tags)

    @Query("SELECT * From tags_table")
    fun getAllTags(): Flow<List<Tags>>

    @Query("Select * From tags_table where tag_name =:tagName")
    fun getTagsWithTask(tagName: String): Flow<List<TagWithTaskLists>>

    @Query("SELECT * FROM task_table WHERE date LIKE :date")
    fun sortByCreationDate(date: String): Flow<List<TaskWithTags>>

    @Upsert
    suspend fun upsertTagList(tag: List<Tags>)


    @Transaction
    @Query("SELECT * FROM task_table")
    fun getTasksWithTags(): Flow<List<TaskWithTags>>

    @Transaction
    @Query("SELECT * FROM tags_table")
    fun getTagWithTaskLists(): Flow<List<TagWithTaskLists>>

    @Transaction
    @Query("SELECT * FROM task_table WHERE task_id LIKE :TaskId Limit 1")
    suspend fun getTasksById(TaskId: Long): TaskWithTags

    @Query("SELECT * FROM task_table WHERE task_title LIKE :taskTitle")
    fun searchForTasks(taskTitle: String): Flow<List<TaskWithTags>>

    @Query("SELECT * FROM task_table WHERE task_title LIKE :taskTitle")
    fun searchByTasks(taskTitle: String): List<TaskWithTags>

    @Query("SELECT * FROM tags_table WHERE tag_name LIKE :tagTitle")
    fun searchByTag(tagTitle: String): List<TagWithTaskLists>

    @Transaction
    suspend fun searchBoth(searchQuery: String): SearchedResult {
        val taskResults = searchByTasks(searchQuery)
        val tagResults = searchByTag(searchQuery)
        return SearchedResult(taskResults, tagResults)
    }

    @Transaction
    @Query("SELECT * FROM task_table")
    fun getAllTaskwithTags(): Flow<List<TaskWithTags>>
//    @Transaction
//    @Query("SELECT * FROM task_table")
//    fun getAllTaskWithTags(): Flow<List<TaskWithTags>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskTagCrossRef(crossRef: TaskTagCrossRef): Long
    @Query("DELETE FROM tasktagcrossref WHERE task_id LIKE :taskId")
    fun deletCrossRef(taskId:Long)

    @Transaction
    suspend fun updateCrossRef(task: Task,tags: List<Tags>){
        addTask(task)

        deletCrossRef(task.taskId!!)

        for(tag in tags){
        upsertTag(tag)
            insertTaskTagCrossRef(TaskTagCrossRef(task.taskId!!,tag.name))
           // insertTaskTagCross(TaskTagCrossRef(task.taskId!!,tag.name))
        }
//the problem is the previouse tags are also removed
//todo when the user press the tag drop down mune for delete and add
    }
}