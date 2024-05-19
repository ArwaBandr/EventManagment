package com.example.eventmanagment.presntation.screens.task

import TaskCard
import android.content.ClipData.Item
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskByDatScreen(filterTasksViewModel: FilterTasksViewModel) {
    var tasks = filterTasksViewModel.filteredTask.value.collectAsState(initial = null)
    var tag = filterTasksViewModel.tags.collectAsState(initial = null).value
    var countHour: Int=0
    var countMinutes: Int=0

//    for (task in tasks.value) {
//        countHour += task.timeFrom.substringBefore(":").toInt()
//        countMinutes += task.timeFrom.substringAfter(":").toInt()
//    }
    Column(Modifier.fillMaxSize()) {
        TaskByDatePicker(filterTasksViewModel)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Today")
            Text(text = "${countHour.toString()} h ${countMinutes.toString()} m")
        }
        LazyColumn(Modifier.fillMaxSize()) {
            val groupedList = tasks.value?.groupBy { it.timeFrom.substringBefore(":") }.orEmpty()

            groupedList.forEach {
                item {
                    LazyRow(Modifier.fillParentMaxWidth() ,horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        item {
                            Text(text = "${it?.key.toString()}:00")
                        }
                        items(it.value) { task ->
                            Box(modifier = Modifier.fillParentMaxWidth(0.6f)){
                                TaskCard(
                                    taskTitle = task.title,
                                    timeFrom = task.timeFrom,
                                    timeTo = task.timeTo,
                                    tag = tag?.find { it.name == task.tagName })
                            }

                        }

                    }
                }
            }
        }

    }

}
