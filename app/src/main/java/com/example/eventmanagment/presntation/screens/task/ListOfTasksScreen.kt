package com.example.eventmanagment.presntation.screens.task

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import com.example.eventmanagment.component.TaskCard
import com.example.eventmanagment.component.TaskHeaderView
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.getIconName
import com.example.eventmanagment.ui.theme.LightPurple

@Composable
fun ListOfTasksScreen(
    title: String,
    filterTasksViewModel: FilterTasksViewModel,
    navController: NavController
) {
    filterTasksViewModel.getListOfTasksByTagName(title)

    var tagAndtasks = filterTasksViewModel.queryTagwithTasks.value.firstOrNull() {
        it.tag.name == title
    }
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            TaskHeaderView(title = title) {
                navController.popBackStack()
            }
        }

        items(tagAndtasks?.tasks.orEmpty()) {
            TaskCard(
                taskTitle = it.title,
                timeFrom = it.timeFrom,
                timeTo = it.timeTo,
                tag = listOf(tagAndtasks?.tag)
            )
        }
    }
}