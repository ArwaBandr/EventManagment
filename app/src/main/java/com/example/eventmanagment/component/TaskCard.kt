package com.example.eventmanagment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.eventmanagment.data.entity.Tags
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.iconByName
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.presntation.screens.task.FilterTasksViewModel
import com.example.eventmanagment.ui.theme.Navy
import com.example.eventmanagment.ui.theme.PrimaryColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskCard(taskObj: Task,taskTitle: String, timeFrom: String?, timeTo: String?, tag: List<Tags?>,navController: NavHostController) {
    val filterTasksViewModel = hiltViewModel<FilterTasksViewModel>()

    val dividerHeight = remember {
        mutableStateOf(50.dp)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(
                tag?.firstOrNull()?.color?.toIntOrNull() ?: PrimaryColor.toArgb()
            ).copy(0.3f)
        )

    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    , Arrangement.SpaceBetween

            ) {

                Row {
                    //divider
                    Box(
                        modifier = Modifier
                            .height(dividerHeight.value)
                            .width(3.dp)
                            .background(
                                Color(
                                    tag?.firstOrNull()?.color?.toIntOrNull()
                                        ?: PrimaryColor.toArgb()
                                ),
                                RoundedCornerShape(16.dp)
                            )
                            .padding(0.dp, 40.dp)
                    )
                    Column(modifier = Modifier
                        .padding(4.dp)
//                        .drawBehind {
//                            drawLine(
//                                Color(tag?.color?.toIntOrNull() ?: PrimaryColor.toArgb()),
//                                Offset(0f, 0F),
//                                Offset(0F, size.height),
//                                2.dp.toPx(),)
//                        }
                        .onGloballyPositioned {
                            dividerHeight.value = it.size.height.dp / 2
                        }) {

                        Text(
                            text = taskTitle,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Navy
                        )
                        Text(text = "$timeFrom - $timeTo", fontSize = 15.sp, color = Color.Gray)
                    }
                }
                //box
                var expanded = remember { mutableStateOf(false) }
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            // navController.navigate("${Screens.MainApp.EditTaskScreen.rout}/${taskObj.taskId}")
                            expanded.value = true
                        }
                )
//                if(expanded) {
//                    DropDownMenu(taskObj,navController) {
//                        CoroutineScope(Dispatchers.IO).launch {
//                        filterTasksViewModel.deletTask(taskObj)}
//                    }

                if (expanded.value) {
                    val items2 = listOf(
                        MenuItem("Disable", Icons.Outlined.Close, { true }),

                        MenuItem(
                            "Edit",
                            Icons.Outlined.Settings,
                            { navController.navigate("${Screens.MainApp.EditTaskScreen.rout}/${taskObj.taskId}") }),

                        MenuItem("Delete", Icons.Outlined.Delete,
                            {
                                filterTasksViewModel.deletTask(taskObj)
                            }
                        )
                    )
                    DropDownMenu(items2 = items2, navController,expanded)
                    }
                }

            }
            FlowRow(
                Modifier
                    .fillMaxWidth()
                    .padding(25.dp, 10.dp), Arrangement.spacedBy(10.dp)
            ) {
                tag?.forEach { tag ->
                    Box(
                        Modifier
                            .background(
                                Color(
                                    tag?.color?.toIntOrNull() ?: PrimaryColor.toArgb()
                                ).copy(0.2f),
                                RoundedCornerShape(16.dp)
                            )
//                        .border(
//                            1.dp,
//                            Color(tag?.color?.toIntOrNull() ?: PrimaryColor.toArgb()),
//                            RoundedCornerShape(16.dp)
//                        )
                    ) {
                        Row {
                            Text(
                                text = tag?.name.orEmpty(),
                                modifier = Modifier.padding(5.dp),
                                color = Color(
                                    tag?.color?.toIntOrNull() ?: PrimaryColor.toArgb()
                                ),

                                )
                            val icon = iconByName(tag?.iconName.orEmpty())
                            Icon(imageVector = icon, contentDescription = "")
                        }
                    }
                }
            }

        }
//to convert color to String and vice versa
//    val color=Color.Gray.toArgb().toString()
//    Color(color.toIntOrNull()?: PrimaryColor.toArgb())

//    }


}

