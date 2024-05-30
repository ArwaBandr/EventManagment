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
    filterTasksViewModel.getListOfTasksByTagName(title)
    var searchedClicked by remember {
        mutableStateOf(false)
    }


//    var tagAndtasks =if (filterTasksViewModel.searchedByTag.value.isEmpty())filterTasksViewModel.queryTagwithTasks.value.firstOrNull() {
//        it.tag.name == title
//    } else filterTasksViewModel.searchedByTag.value.firstOrNull(){
//        it.tag.name==title
//    }

    var tagAndtasks =
        if (filterTasksViewModel.searchedByTag.value.isEmpty()) filterTasksViewModel.queryTagwithTasks.value.firstOrNull() {
            it.tag.name == title
        } else filterTasksViewModel.searchedByTag.value.firstOrNull()


//    var searchedList: List<TagWithTaskLists> = emptyList()
//    var tag:Tags
//    var task:Task
//    filterTasksViewModel.searchedTasks.value.forEach {
//        it.tag.forEach { stags ->
//            if (stags.name == title) {
//               tag = Tags(stags.name, stags.color, stags.iconName)
//            }
//        }
//        task = Task(it.task.taskId,it.task.title,it.task.description,it.task.date,it.task.timeFrom,it.task.timeTo,it.task.taskType,it.task.tagName)
//    }


    LazyColumn(Modifier.fillMaxSize()) {
        item {
            TaskHeaderView(title = title) {
                navController.popBackStack()
            }
        }

        item { SearchBar(filterTasksViewModel = filterTasksViewModel) }

        items(tagAndtasks?.tasks.orEmpty()) {
            TaskCard(
                taskObj = it,
                taskTitle = it.title,
                timeFrom = it.timeFrom,
                timeTo = it.timeTo,
                tag = listOf(tagAndtasks?.tag),
                navController
            )
        }
    }
}
