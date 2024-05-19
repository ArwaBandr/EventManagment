package com.example.eventmanagment.presntation.screens.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.eventmanagment.component.AddTagsListView
import com.example.eventmanagment.component.CustomTextField
import com.example.eventmanagment.component.TaskHeaderView
import com.example.eventmanagment.component.TimePickerDialog
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.ui.theme.PrimaryColor

@Composable
fun AddTaskScreen(navController: NavHostController, addTaskViewModel: AddTaskViewModel) {
    val allTags = addTaskViewModel.allTags.collectAsState(initial = null)

    val showStartTimeTimeDialog = remember {
        mutableStateOf(false)
    }

    val showEndTimeTimeDialog = remember {
        mutableStateOf(false)
    }
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)) {
        item {
            TaskHeaderView("Add Task"){
                navController.popBackStack()
            }
        }
        //task fields
        item {
            CustomTextField(Modifier, "Title", Color.Gray, addTaskViewModel.title, isReadOnly = false)
            CustomTextField(
                Modifier,
                "Date",
                Color.Gray,
                addTaskViewModel.taskDate,
                isReadOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.DateRange, "", modifier =
                    Modifier.clickable {
                        navController.navigate(Screens.MainApp.DateDailog.rout)
                    })
                })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomTextField(
                    Modifier
                        .weight(1f)
                        .clickable {
                            showStartTimeTimeDialog.value = true
                        },
                    "Time From", Color.Gray, addTaskViewModel.startDate, isReadOnly = true,
                )
                CustomTextField(
                    Modifier
                        .weight(1f)
                        .clickable {
                            showEndTimeTimeDialog.value = true
                        },
                    "Time To", Color.Gray, addTaskViewModel.endDate, isReadOnly = true,
                )
            }
            CustomTextField(
                Modifier,
                "Description",
                Color.Gray,
                addTaskViewModel.describtion,
                isReadOnly = false
            )

        }
        item {
            AddTagsListView(allTags.value.orEmpty()){
                addTaskViewModel.category.value= it.name
            }
        }
        item {
            ButtonAddTask(addTaskViewModel)
        }
    }
    if (showStartTimeTimeDialog.value) {
        TimePickerDialog(
            onBackPress = {
                showStartTimeTimeDialog.value = false
            },
            onTimeSelected = { hour, minute ->
                addTaskViewModel.startDate.value = "$hour:$minute"
            }
        )
    }
    if (showEndTimeTimeDialog.value) {
        TimePickerDialog(
            onBackPress = {
                showEndTimeTimeDialog.value = false
            },
            onTimeSelected = { hour, minute ->
                addTaskViewModel.endDate.value = "$hour:$minute"
            }
        )
    }
}

@Composable
private fun ButtonAddTask(addTaskViewModel: AddTaskViewModel) {
    Button(
        onClick = {
            addTaskViewModel.addTask()
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)
            .semantics {
                testTag = "Add Task Button"
            },
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
    ) {
        Text(
            text = "Create", modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp,
            color = Color.White
        )
    }
}