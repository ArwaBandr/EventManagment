package com.example.eventmanagment.presntation.screens.task

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
class TaskViewModel @Inject constructor(private val taskRepositry: TaskRepositry):ViewModel(){
    val tasks =taskRepositry.getAllTasks()
    val tags =taskRepositry.getAllTags()
    val taskByTags =taskRepositry.getTagsWithTag("Personal")

    init {
        viewModelScope.launch {

            taskRepositry.insertTag(
                Tags(
                    "Work",
                    "color"
                )

            )
            taskRepositry.insertTag(
                Tags(
                    "Work",
                    "color"
                )
            )

            taskRepositry.insertTask(
                Task(
                    title = "title",
                    description = "description",
                    date = "2022-2-02",
                    taskType = TaskType.OnGoing.type,
                    timeFrom = "10:20",
                    timeTo = "12:10",
                    tagName = "Work"
                    )
            )

            taskRepositry.insertTask(
                Task(
                    title = "title2",
                    description = "description2",
                    date = "",
                    taskType = TaskType.OnGoing.type,
                    timeFrom = "10:202",
                    timeTo = "12:102",
                    tagName = "Personal"
                )
            )
        }


    }

}