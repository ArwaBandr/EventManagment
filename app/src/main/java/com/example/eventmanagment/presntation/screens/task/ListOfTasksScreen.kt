package com.example.eventmanagment.presntation.screens.task

import android.nfc.Tag
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.eventmanagment.component.SearchBar
import com.example.eventmanagment.component.TaskCard
import com.example.eventmanagment.component.TaskHeaderView
import com.example.eventmanagment.data.entity.TagWithTaskLists
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.data.entity.TaskWithTags

@Composable
fun ListOfTasksScreen(
    title: String,
    filterTasksViewModel: FilterTasksViewModel,
    navController: NavHostController
) {
    //filterTasksViewModel.getListOfTasksByTagName(title)
    LaunchedEffect(Unit) {
        filterTasksViewModel.getTasksByTagName(title)
    }


var tagAndtasks = filterTasksViewModel.queryTagwithTasks.value.firstOrNull(){
    it.tag.name==title
}

    var result = filterTasksViewModel.taskWithTags_.value
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            TaskHeaderView(title = title) {
                navController.popBackStack()
            }
        }

        item { SearchBar(filterTasksViewModel = filterTasksViewModel) }

//        items(tagAndtasks?.tasks.orEmpty()) {
//            TaskCard(
//                taskObj = it,
//                taskTitle = it.title,
//                timeFrom = it.timeFrom,
//                timeTo = it.timeTo,
//                tag = listOf(tagAndtasks?.tag),
//                navController
//            )
//        }
        items(result) {
            TaskCard(
                taskObj = it.task,
                taskTitle = it.task.title,
                timeFrom = it.task.timeFrom,
                timeTo = it.task.timeTo,
                tag = it.tag,
                navController
            )
        }
    }
}
