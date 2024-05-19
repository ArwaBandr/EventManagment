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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(val taskRepositry: TaskRepositry) : ViewModel() {
    val title: MutableState<String> = mutableStateOf("")
    val describtion: MutableState<String> = mutableStateOf("")
    val taskDate: MutableState<String> = mutableStateOf("")
    val startDate: MutableState<String> = mutableStateOf("")
    val endDate: MutableState<String> = mutableStateOf("")
    val taskType: MutableState<String> = mutableStateOf(TaskType.OnGoing.type)
    val category: MutableState<String> = mutableStateOf("")

    var allTasks = taskRepositry.getAllTasks()
    val allTags =  taskRepositry.getAllTags()
    fun addTask() {
        viewModelScope.launch {
            val task = Task(
                title = title.value,
                description = describtion.value,
                date = taskDate.value,
                taskType = taskType.value,
                timeFrom = startDate.value,
                timeTo = endDate.value,
                tagName = category.value
            )
            taskRepositry.insertTask(task = task)
        }
    }

    fun addTags(list: List<Tags>) {
        viewModelScope.launch {
            taskRepositry.insertTagList(list)
        }
    }

}