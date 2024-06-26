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
 import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.res.stringResource
 import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
 import com.example.eventmanagment.R
 import com.example.eventmanagment.component.AddTagsListView
import com.example.eventmanagment.component.CustomTextField
import com.example.eventmanagment.component.TaskHeaderView
import com.example.eventmanagment.component.TimePickerDialog
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.ui.theme.PrimaryColor

@Composable
fun AddTaskScreen(navController: NavHostController, addTaskViewModel: AddTaskViewModel) {

    val allTags = addTaskViewModel.allTags.collectAsState(initial = emptyList())

    val showStartTimeTimeDialog = remember {
        mutableStateOf(false)
    }

    val showEndTimeTimeDialog = remember {
        mutableStateOf(false)
    }
    LazyColumn(modifier = Modifier
        .padding(horizontal = 16.dp)) {
        item {
            TaskHeaderView(stringResource(id = R.string.add_task)){
                navController.popBackStack()
            }
        }
        //task fields
        item {
            CustomTextField(Modifier, stringResource(id = R.string.title), Color.Gray, addTaskViewModel.title, isReadOnly = false)
            CustomTextField(
                Modifier,
                stringResource(id = R.string.date),
                Color.Gray,
                addTaskViewModel.taskDate,
                isReadOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.DateRange, "", modifier =
                    Modifier.clickable {
                        navController.navigate(Screens.MainApp.DateDailog.rout)
                    })
                })}
        item {
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
                    stringResource(id = R.string.time_from), Color.Gray, addTaskViewModel.startDate, isReadOnly = true,
                )
                CustomTextField(
                    Modifier
                        .weight(1f)
                        .clickable {
                            showEndTimeTimeDialog.value = true
                        },
                    stringResource(id = R.string.time_to), Color.Gray, addTaskViewModel.endDate, isReadOnly = true,
                )
            }
        }
        item {
            CustomTextField(
                Modifier,
                stringResource(id = R.string.description),
                Color.Gray,
                addTaskViewModel.describtion,
                isReadOnly = false
            )
        }

        item {
            AddTagsListView(allTags.value.orEmpty(),navController){
               // addTaskViewModel.category.value=it.name
                addTaskViewModel.selectedTags.value = it
            }
        }
        item {
            ButtonAddTask(addTaskViewModel,navController)
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
private fun ButtonAddTask(addTaskViewModel: AddTaskViewModel, navController: NavHostController) {
    Button(
        onClick = {
            addTaskViewModel.addTask()
            navController.popBackStack()
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp, bottom = 100.dp)
            .semantics {
                testTag = "Add Task Button"
            },
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
    ) {
        Text(
            text = stringResource(id = R.string.create), modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp,
            color = Color.White
        )
    }
}