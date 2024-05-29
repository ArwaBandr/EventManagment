package com.example.eventmanagment.presntation.screens.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventmanagment.component.AddTagsListView
import com.example.eventmanagment.component.CustomTextField
import com.example.eventmanagment.component.TaskHeaderView
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.ui.theme.PrimaryColor

@Composable
fun EditTask(taskID: Long, navController: NavHostController ,viewModel: AddTaskViewModel) {

    LaunchedEffect(taskID) {
        if (taskID != null){
            viewModel.selectedTaskwithTags(taskID = taskID)
        }
    }

    val allTags = viewModel.allTags.collectAsState(initial = emptyList())


//    LaunchedEffect(key1 = true) {
//
//        ViewModel.selectedTaskwithTags(taskID = taskID)
//
//    }



    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        item {
            TaskHeaderView("Edit Task") {
                navController.popBackStack()
            }
        }

        //task fields
        item {
            CustomTextField(Modifier, "Title", Color.Gray, viewModel.title, isReadOnly = false)
            CustomTextField(
                Modifier,
                "Date",
                Color.Gray,
                viewModel.taskDate,
                isReadOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.DateRange, "", modifier =
                    Modifier.clickable {
                        navController.navigate(Screens.MainApp.DateDailog.rout)
                    })
                })
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomTextField(
                    Modifier
                        .weight(1f)
                        .clickable {

                        },
                    "Time From", Color.Gray, viewModel.startDate, isReadOnly = true,
                )
                CustomTextField(
                    Modifier
                        .weight(1f)
                        .clickable {

                        },
                    "Time To", Color.Gray, viewModel.endDate, isReadOnly = true,
                )
            }
        }
        item {
            CustomTextField(
                Modifier,
                "Description",
                Color.Gray,
                viewModel.describtion,
                isReadOnly = false
            )
        }

        item {
            AddTagsListView(allTags.value.orEmpty(), navController) {
                // addTaskViewModel.category.value=it.name
                viewModel.selectedTags.value = it
//                CoroutineScope(Dispatchers.IO).launch {
//                    filterTasksViewModel.deletTag(it)
//                }
            }
        }
        item {
            ButtonEditTask(navController = navController, viewModel, taskID)
        }
    }

}

@Composable
private fun ButtonEditTask(
    navController: NavHostController,
    addTaskViewModel: AddTaskViewModel,
    taskID: Long
) {
    Button(
        onClick = {
            // addTaskViewModel.editTask
            addTaskViewModel.editTask(taskID)
            navController.popBackStack()
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp, bottom = 100.dp)
            .semantics {
                testTag = "Edit Task Button"
            },
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
    ) {
        Text(
            text = "Edit", modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp,
            color = Color.White
        )
    }
}