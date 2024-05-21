package com.example.eventmanagment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.eventmanagment.presntation.screens.task.FilterTasksViewModel

@Composable
fun DropDownMenu(navController: NavController) {
    //filterTasksViewModel: FilterTasksViewModel
    var expanded by remember { mutableStateOf(true) }
    val items = mapOf(
        Icons.Outlined.Close to "Disable",
        Icons.Outlined.Settings to "Edit",
        Icons.Outlined.Delete to "Delete"
    )
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .wrapContentSize(Alignment.TopStart)
        .background(Color.White)) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false
                               navController.popBackStack()},
            offset = DpOffset(45.dp, 24.dp),
            properties = PopupProperties(dismissOnClickOutside = true, dismissOnBackPress = true),
            modifier = Modifier
                .background(
                    Color.LightGray
                )
        ) {
            items.entries.withIndex().forEachIndexed() {index, element->
                DropdownMenuItem(text = { Text(text = element.value.value) }, onClick = { selectedIndex= index}, trailingIcon = {
                    Icon(
                        imageVector = element.value.key,
                        contentDescription = ""
                    )
                })

            }

        }
//        LaunchedEffect(Unit) {
//            if(!expanded){
//                navController.popBackStack()
//            }
//        }
    }

}

