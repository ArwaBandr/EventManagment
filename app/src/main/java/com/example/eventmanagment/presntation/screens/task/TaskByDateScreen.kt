package com.example.eventmanagment.presntation.screens.task

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.eventmanagment.component.TaskByDatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import java.time.LocalDate
import com.example.eventmanagment.R
import com.example.eventmanagment.component.SearchBar
import com.example.eventmanagment.component.TaskCard
import com.example.eventmanagment.ui.theme.textcolor

@Composable
fun TaskByDatScreen(filterTasksViewModel: FilterTasksViewModel, navController: NavHostController) {
    // var tasks = filterTasksViewModel.filteredTask.value.collectAsState(initial = null)
//    LaunchedEffect(Unit) {
//        filterTasksViewModel.filterTaskByDate(LocalDate.now().toString())
//    }

    //var tasks = filterTasksViewModel.taskWithTags

    var tasks =
        if (filterTasksViewModel.searchedTasks.value.isEmpty()) filterTasksViewModel.taskWithTags else filterTasksViewModel.searchedTasks
    var tag = filterTasksViewModel.tags.collectAsState(initial = null).value
    var countHour: Int = 0
    var countMinutes: Int = 0

//    for (task in tasks.value) {
//        countHour += task.timeFrom.substringBefore(":").toInt()
//        countMinutes += task.timeFrom.substringAfter(":").toInt()
//    }

    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(top = 5.dp))
        SearchBar(filterTasksViewModel)
        TaskByDatePicker(filterTasksViewModel)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Today", color = textcolor, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Text(text = "${countHour.toString()} h ${countMinutes.toString()} m")
        }
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val groupedList =
                tasks.value.groupBy { it.task.timeFrom.substringBefore(":") }.orEmpty()
            if (groupedList.isNotEmpty()) {
                item {
                    Divider(Modifier.fillMaxWidth(), color = Color.Gray, thickness = 10.dp)
                }
                groupedList.forEach {
                    item {
                        LazyRow(
                            Modifier.fillParentMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            item {
                                Text(text = "${it.key.toString()}:00")
                            }
                            items(it.value) { taskWithTag ->
                                Box(modifier = Modifier.fillParentMaxWidth(0.6f)) {
                                    TaskCard(
                                        taskObj = taskWithTag.task,
                                        taskTitle = taskWithTag.task.title,
                                        timeFrom = taskWithTag.task.timeFrom,
                                        timeTo = taskWithTag.task.timeTo,
                                        tag = taskWithTag.tag,
                                        navController
                                    )
                                }
                            }

                        }
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_tasks_image),
                            contentDescription = ""
                        )
                        Text(text = "No tasks for the day")
                    }
                }


            }


        }

    }

}
