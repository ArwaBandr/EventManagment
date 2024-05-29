package com.example.eventmanagment.data.repositry

import com.example.eventmanagment.data.dao.TaskDao
import com.example.eventmanagment.data.entity.TagWithTaskLists
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskTagCrossRef
import com.example.eventmanagment.data.entity.TaskWithTags
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositry @Inject constructor(private val taskDao: TaskDao) {

    suspend fun insertTaskTagCrossRefs(taskTagCrossRef: List<TaskTagCrossRef>){
        taskDao.insertTaskTagCross(taskTagCrossRef)

    }

    suspend fun insertTask(task: Task):Long{
       return taskDao.addTask(task = task)
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

    fun getTagsWithTag(tagName: String):Flow<List<TagWithTaskLists>>{
        return taskDao.getTagsWithTask(tagName)
    }

    fun getAllTags():Flow<List<Tags>>{
        return taskDao.getAllTags()
    }

    suspend fun insertTagList(tagList: List<Tags>){
         taskDao.upsertTagList(tagList)
    }

     fun sortTaskByCreationDate(date: String):Flow<List<TaskWithTags>>{
        return taskDao.sortByCreationDate(date)
    }
    fun getTaskWithTasgs():Flow<List<TaskWithTags>>{
        return taskDao.getTasksWithTags()
    }

    fun getTagsWithTasksList()= taskDao.getTagWithTaskLists()

    suspend fun selectedTaskwithTags(taskID:Long)=taskDao.getTasksById(taskID)

    suspend fun insertTaskWithTags(task: Task, tags:List<Tags>) =taskDao.insertTaskWithTags(task,tags)

    suspend fun searchForTasks(taskTitle:String)= taskDao.searchForTasks(taskTitle)

    suspend fun searchByTag(tagTitle: String)=taskDao.searchByTag(tagTitle)

    suspend fun searchBoth(query:String) =taskDao.searchBoth(query)
}