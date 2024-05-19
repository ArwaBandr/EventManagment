package com.example.eventmanagment.data.repositry

import com.example.eventmanagment.data.dao.TaskDao
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskWithTagLists
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositry @Inject constructor(private val taskDao: TaskDao) {

    suspend fun insertTask(task: Task){
        taskDao.addTask(task = task)
    }

    suspend fun deletTask(task: Task){
        taskDao.deleteTask(task = task)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTask()
    }

    suspend fun insertTag(tags: Tags){
        taskDao.upsertTag(tags)
    }

    suspend fun deletTag(tags: Tags){
        taskDao.deletTag(tags)
    }

    fun getTagsWithTag(tagName: String):Flow<List<TaskWithTagLists>>{
        return taskDao.getTagsWithTask(tagName)
    }

    fun getAllTags():Flow<List<Tags>>{
        return taskDao.getAllTags()
    }

    suspend fun insertTagList(tagList: List<Tags>){
         taskDao.upsertTagList(tagList)
    }

     fun sortTaskByCreationDate(date: String):Flow<List<Task>>{
        return taskDao.sortByCreationDate(date)
    }
//    fun getTaskDate():Boolean{
//    }
}