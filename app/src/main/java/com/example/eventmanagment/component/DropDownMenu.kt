package com.example.eventmanagment.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventmanagment.data.entity.Task
import com.example.eventmanagment.iconByName
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.presntation.navigation.popUpToTop
import com.example.eventmanagment.presntation.screens.task.EditTask
import com.example.eventmanagment.presntation.screens.task.FilterTasksViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//fun DropDownMenu(taskObj: Task, navController: NavHostController) {
@Composable
fun DropDownMenu(items2:List<MenuItem>, navController: NavHostController, expanded: MutableState<Boolean>) {
    val viewModel = hiltViewModel<FilterTasksViewModel>()
    //var expanded by remember { mutableStateOf(true) }
    val items = mapOf(
        Icons.Outlined.Close to "Disable",
        Icons.Outlined.Settings to "Edit",
        Icons.Outlined.Delete to "Delete"
    )

//    val items2 = listOf(
//        MenuItem("Disable", Icons.Outlined.Close, { true }),
//
//        MenuItem(
//            "Edit",
//            Icons.Outlined.Settings,
//            { navController.navigate("${Screens.MainApp.EditTaskScreen.rout}/${taskObj.taskId}") }),
//
//        MenuItem("Delete", Icons.Outlined.Delete,
//            {
//                viewModel.deletTask(taskObj)
//            }
//        )
//    )


    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .background(Color.White)
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
                //navController.popBackStack()
            },
            offset = DpOffset(10.dp, 10.dp),
            properties = PopupProperties(dismissOnClickOutside = true, dismissOnBackPress = true),
            modifier = Modifier
                .background(
                    Color.LightGray
                )
        ) {
//            items.entries.withIndex().forEachIndexed() { index, element ->
//                DropdownMenuItem(
//                    text = { Text(text = element.value.value) },
//                    onClick = {
//                        selectedIndex = index
//
//                    },
//                    trailingIcon = {
//                        Icon(
//                            imageVector = element.value.key,
//                            contentDescription = ""
//                        )
//                    })
//            }

            items2.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.title)},
                    onClick = { it.onClick.invoke() },
                    trailingIcon = {
                        Icon(imageVector = it.Icon, contentDescription = "")
                    })
            }
        }

      //  var context = LocalContext.current
//        when (selectedIndex) {
//            0 -> {
//                Toast.makeText(context, "first", Toast.LENGTH_SHORT).show()
//            }
//
//            1 -> {
//                navController.navigate("${Screens.MainApp.EditTaskScreen.rout}/${taskObj.taskId}")
//                expanded = false
//                // EditTask(it.arguments?.getLong("taskID"), navController)
//
//            }
//
//            2 -> {
//                onClick.invoke()
//            }
//
//            else -> {}
//
//        }
    }

}

data class MenuItem(
    val title: String,
    val Icon: ImageVector,
    val onClick: () -> Unit
)
