package com.example.eventmanagment.presntation.screens.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventmanagment.component.CustomTextField
import com.example.eventmanagment.component.TaskHeaderView
import com.example.eventmanagment.getAllColors
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import com.example.eventmanagment.getAllSystemIcons
import com.example.eventmanagment.getIconName

@Composable
fun AddNewTag(addTaskViewModel: AddTaskViewModel,navController:NavController) {
    var value = rememberSaveable {
        mutableStateOf("")
    }
    Box(Modifier.wrapContentSize().background(color = Color.LightGray)) {
        Column(Modifier.wrapContentSize(), verticalArrangement = Arrangement.SpaceBetween) {
            //Text(text = "New Tag", )
            TaskHeaderView(title = "New Tag") {
                navController.popBackStack()
            }
            CustomTextField(
                label = "tag name",
                textColor = Color.Black,
                value = addTaskViewModel.tagName,
                isReadOnly = false
            )

            LazyRow (Modifier.fillMaxWidth()){
                items(getAllColors().size) {
                    getAllColors().forEach {
                        Canvas(modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                addTaskViewModel.tagColor.value = it
                                    .toArgb()
                                    .toString()
                            }) {
                            drawCircle(it)
                        }

                    }
                }

            }

            Spacer(modifier = Modifier.size(22.dp))

            LazyRow (Modifier.fillMaxWidth()){
                items(getAllSystemIcons().size) {
                    getAllSystemIcons().forEach {
                        Icon(imageVector = it, contentDescription = "", modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                addTaskViewModel.tagIcon.value = getIconName(it)
                            })
                    }
                }

            }
            Row (Modifier.padding(5.dp)){
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        navController.popBackStack()
                    }, shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Cancel")

                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        addTaskViewModel.addTags()
                        navController.popBackStack()
                    }, shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Save", color = Color.White)
                }
            }



        }
    }
}
