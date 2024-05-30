package com.example.eventmanagment.presntation.screens.task


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.eventmanagment.R
import com.example.eventmanagment.component.TaskCard
import com.example.eventmanagment.component.TaskCategeryCard
import com.example.eventmanagment.data.entity.TaskType
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.ui.theme.textcolor
import com.google.firebase.auth.FirebaseUser
import java.time.LocalDate

@Composable
fun HomeScreen(
    firebaseUser: FirebaseUser?,
    filterTasksViewModel: FilterTasksViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        filterTasksViewModel.filterTaskByDate(LocalDate.now().toString())
    }
    val completedTask = filterTasksViewModel.completedTasks.collectAsState(initial = null)
    val cancelledTask = filterTasksViewModel.cancelledTasks.collectAsState(initial = null)
    val onGoingTask = filterTasksViewModel.onGoingTasks.collectAsState(initial = null)
    val pendingTask = filterTasksViewModel.pendingTasks.collectAsState(initial = null)
   // val addViewMode = hiltViewModel<AddTaskViewModel>()

    val tasksList = filterTasksViewModel.taskWithTags

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .semantics {
                contentDescription = "Home Screen"
            }, verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        item {
            HeaderView(firebaseUser)
            Spacer(modifier = Modifier.size(5.dp))
        }
        item {
            Row(
                Modifier
                    //.fillMaxWidth()
                    //.wrapContentHeight()
                    .padding(vertical = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(vertical = 12.dp)
                ) {
                    TaskCategeryCard(
                        height = 220.dp,
                        //title = TaskType.Completed.type,
                        title = stringResource(id = R.string.completed),
                        tintColor = Color(0xFF7DC8E7),
                        totalTasksInCategory = completedTask.value?.first()?.tasks?.size.toString(),
                        onClick = { navController.navigate("${Screens.MainApp.LisOfTasksScreen.rout}/${TaskType.Completed.type}")},
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.imac_2),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )
                    }


                    TaskCategeryCard(
                        height = 190.dp,
                        //title = TaskType.Cancelled.type,
                        title = stringResource(id = R.string.cancelled),
                        tintColor = Color(0xFFE77D7D),
                        totalTasksInCategory = cancelledTask.value?.first()?.tasks?.size.toString(),
                        onClick = {navController.navigate("${Screens.MainApp.LisOfTasksScreen.rout}/${TaskType.Cancelled.type}") },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_square),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)

                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(vertical = 12.dp)
                ) {
                    TaskCategeryCard(
                        height = 190.dp,
                        title = stringResource(id = R.string.pending),
                        tintColor = Color(0xFF7D88E7),
                        totalTasksInCategory = pendingTask.value?.first()?.tasks?.size.toString(),
                        onClick = {navController.navigate("${Screens.MainApp.LisOfTasksScreen.rout}/${TaskType.Pending.type}") },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.time_square),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)

                        )
                    }
                    TaskCategeryCard(
                        height = 220.dp,
                        title = stringResource(id = R.string.on_going),
                        tintColor = Color(0xFF81E89E),
                        totalTasksInCategory = onGoingTask.value?.first()?.tasks?.size.toString(),
                        onClick = {navController.navigate("${Screens.MainApp.LisOfTasksScreen.rout}/${TaskType.OnGoing.type}") },
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.CheckCircle,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)

                        )
                    }
                }
            }
        }
        //tasks list of the day
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.today_tasks),
                    fontSize = 24.sp,
                    color = textcolor,
                    fontWeight = FontWeight.Bold
                )

                Text(text = stringResource(id = R.string.view_all), color = textcolor, modifier = Modifier.clickable {
                    navController.navigate(Screens.MainApp.TaskByDate.rout)
                })

            }
        }
        items(tasksList.value) {
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp), color = Color.Gray, thickness = 5.dp
            )
            TaskCard(
                taskObj = it.task,
                taskTitle = it.task.title,
                timeFrom = it.task.timeFrom,
                timeTo = it.task.timeTo,
                tag = it.tag,
                navController
            )
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 100.dp))

        }
    }
}

@Composable
private fun HeaderView(firebaseUser: FirebaseUser?) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(30.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = firebaseUser?.displayName.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = textcolor
            )
            if (firebaseUser?.photoUrl.toString().isEmpty()) {
                Icon(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = ""
                )
            } else {
                AsyncImage(
                    model = firebaseUser?.photoUrl,
                    contentDescription = "user photo",
                    modifier = Modifier
                        .height(42.dp)
                        .width(42.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Row {
            Text(text = stringResource(id = R.string.welcome_msg), color = Color.Gray)
        }
        Text(
            text = stringResource(id = R.string.my_task),
            fontSize = 24.sp,
            color = textcolor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )

    }
}




