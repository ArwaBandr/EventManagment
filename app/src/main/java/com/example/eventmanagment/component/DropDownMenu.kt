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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventmanagment.presntation.screens.task.FilterTasksViewModel

@Composable
fun DropDownMenu( ) {
    //filterTasksViewModel: FilterTasksViewModel
    var expanded by remember { mutableStateOf(false) }
    val items = mapOf(
        Icons.Outlined.Close to "Disable",
        Icons.Outlined.Settings to "Edit",
        Icons.Outlined.Delete to "Delete"
    )
    val EditIcon = Icons.Outlined.Settings
    val disabledValue = "Disable"
    var selectedIndex by remember { mutableStateOf(0) }
Box(modifier = Modifier.wrapContentSize().background(Color.White)) {
    DropdownMenu(
        expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
            .size(100.dp)
            .background(
                Color.LightGray
            )
    ) {
        items.forEach {
            DropdownMenuItem(text = { it.value }, onClick = { }, trailingIcon = {
                Icon(
                    imageVector = it.key,
                    contentDescription = ""
                )
            })

        }

    }
}
}
@Preview(showBackground = true)
@Composable
fun DropDownMenuPreview(){
    DropDownMenu()
}
     