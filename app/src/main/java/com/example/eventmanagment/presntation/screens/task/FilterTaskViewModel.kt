package com.example.eventmanagment.presntation.screens.task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskType
import com.example.eventmanagment.data.repositry.TaskRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class FilterTasksViewModel @Inject constructor(val taskRepositry: TaskRepositry ): ViewModel(){
    val filteredTask = mutableStateOf (taskRepositry.getAllTasks() )
    val tags = taskRepositry.getAllTags()
    val cancelledTasks = taskRepositry.getTagsWithTag(TaskType.Cancelled.type)
    val pendingTasks = taskRepositry.getTagsWithTag(TaskType.Pending.type)
    val completedTasks = taskRepositry.getTagsWithTag(TaskType.Completed.type)
    val onGoingTasks = taskRepositry.getTagsWithTag(TaskType.OnGoing.type)

    init {
        //add base tags
        viewModelScope.launch {
            val tagsList = TaskType.entries.map {
                Tags(it.type, it.color)
            }
            taskRepositry.insertTagList(tagsList)
        }

    }
    fun filterTaskByDate(date:String){
        filteredTask.value =taskRepositry.sortTaskByCreationDate(date = date)
    }



}