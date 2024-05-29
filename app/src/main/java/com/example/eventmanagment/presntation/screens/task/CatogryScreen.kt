package com.example.eventmanagment.presntation.screens.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.eventmanagment.R
import com.example.eventmanagment.component.DropDownMenu
import com.example.eventmanagment.component.TagCard
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.ui.theme.PrimaryColor
import com.google.firebase.auth.FirebaseUser

@Composable
fun CategoryScreen(
    navController: NavHostController,
    firbaseUser: FirebaseUser?,
    viewModel: FilterTasksViewModel
) {

    var tagWithTaskLists = viewModel.tagWithTasks.value
//.verticalScroll(rememberScrollState())
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                      // DropDownMenu(taskObj = , navController =navController )
                    })

            }

        }

        if (firbaseUser?.photoUrl.toString().isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = ""
            )
        } else {
            AsyncImage(
                model = firbaseUser?.photoUrl, contentDescription = "", modifier = Modifier
                    .height(42.dp)
                    .width(42.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = firbaseUser?.displayName.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = firbaseUser?.email.toString())

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            columns = GridCells.Fixed(2)
        ) {

            items(tagWithTaskLists) {
                TagCard(
                    tagName = it.tag.name,
                    tagIcon = it.tag.iconName,
                    tagNumber = "${it.tasks.size} Task",
                    tagColor = Color(it.tag.color.toIntOrNull() ?: PrimaryColor.toArgb())
                ) {
                    navController.navigate("${Screens.MainApp.LisOfTasksScreen.rout}/${it.tag.name.toString()}")
                }
            }
        }
    }
}
