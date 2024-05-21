package com.example.eventmanagment.presntation.screens.task

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskTagCrossRef
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

    //tags
    val tagName: MutableState<String> = mutableStateOf("")
    val tagColor: MutableState<String> = mutableStateOf("")
    val tagIcon: MutableState<String> = mutableStateOf("")
    val allTags = taskRepositry.getAllTags()


    val selectedTags = mutableStateOf<Set<Tags>>(emptySet())

    //var allTasks = taskRepositry.getAllTasks()
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
            insertTaskWithTags(
                task,
                selectedTags.value.toList()
            )
        }
    }

    fun addTags() {
        viewModelScope.launch {
            taskRepositry.insertTag(
                Tags(
                    tagName.value,
                    tagColor.value,
                    tagIcon.value
                )
            )
        }
    }

    private suspend fun insertTaskWithTags(task: Task, tags: List<Tags>) {
        val taskId = taskRepositry.insertTask(task)
        val taskTaggCrossRefs =
            tags.map { TaskTagCrossRef(taskId, it.name) }
        taskRepositry.insertTaskTagCrossRefs(taskTaggCrossRefs)
    }
}