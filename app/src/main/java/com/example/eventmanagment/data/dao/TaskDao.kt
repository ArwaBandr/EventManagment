package com.example.eventmanagment.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eventmanagment.data.entity.Task
@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table")
    fun getAll():List<Task>

    @Delete
    fun delete(task: Task)

    @Insert
    fun insert(vararg task: Task)

}