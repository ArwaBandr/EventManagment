package com.example.eventmanagment.presntation.screens.task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmanagment.data.entity.TagWithTaskLists
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskType
import com.example.eventmanagment.data.entity.TaskWithTags
import com.example.eventmanagment.data.repositry.TaskRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class FilterTasksViewModel @Inject constructor(val taskRepositry: TaskRepositry) : ViewModel() {
    // val filteredTask = mutableStateOf(taskRepositry.getAllTasks())
   // val filteredTask = mutableStateOf<List<Task>>(emptyList())

    val tags = taskRepositry.getAllTags()
    val tasks = mutableStateOf<List<Task>>(emptyList())

    val cancelledTasks = taskRepositry.getTagsWithTag(TaskType.Cancelled.type)
    val pendingTasks = taskRepositry.getTagsWithTag(TaskType.Pending.type)
    val completedTasks = taskRepositry.getTagsWithTag(TaskType.Completed.type)
    val onGoingTasks = taskRepositry.getTagsWithTag(TaskType.OnGoing.type)
    val tagWithTasks = mutableStateOf<List<TagWithTaskLists>>(emptyList())
     val taskWithTags =  mutableStateOf<List<TaskWithTags>> (emptyList())
    val queryTagwithTasks = mutableStateOf<List<TagWithTaskLists>>(emptyList())
    init {
        //add base tags
        viewModelScope.launch {
            val tagsList = TaskType.entries.map {
                Tags(it.type, it.color, it.icon)
            }
            taskRepositry.insertTagList(tagsList)
        }
        getTagWithTaskLists()
    }

    fun filterTaskByDate(date: String) {
        viewModelScope.launch {
            taskRepositry.sortTaskByCreationDate(date).collect {
              //  filteredTask.value = it
                taskWithTags.value=it
            }
        }
    }

    private fun getTagWithTaskLists() {
        viewModelScope.launch {
            taskRepositry.getTagsWithTasksList().collect {
                tagWithTasks.value = it
            }
        }

    }

    fun getListOfTasksByTagName(tagName:String) {
        viewModelScope.launch {
        taskRepositry.getTagsWithTag(tagName).collect{
            queryTagwithTasks.value=it
        }
        }
    }
    suspend fun deletTask(task: Task){
        taskRepositry.deletTask(task)
    }
}