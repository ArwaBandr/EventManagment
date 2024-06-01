package com.example.eventmanagment.presntation.screens.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.eventmanagment.Translation

@Composable
fun SettingScreen(navController: NavHostController, viewModel: AddTaskViewModel) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        TaskHeaderView(title = "Settings") {
//            navController.popBackStack()
//        }
    val context = LocalContext.current
//    val checkedState = remember {
//        mutableStateOf("en")
//    }

    val checkedInDatastore by viewModel.getLanguageSettings().collectAsState(initial = false)
    var checked by remember {
        mutableStateOf(checkedInDatastore)
    }
    var checkedState by remember { mutableStateOf(if (checked) "ar" else "en") }
    LaunchedEffect(checkedInDatastore) {
        checked = checkedInDatastore
        checkedState = if (checked) "ar" else "en"
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = checkedState)
        //Translation.SetLanguage(local = checkedState.value)

        Translation.SetLanguage(local = checkedState)
        Switch(
            checked = checked,
            onCheckedChange = {
                checkedState = if (it) "ar" else "en"
                checked = it
                viewModel.changeLangugeDataStore(it)
                navController.popBackStack()
            },
            thumbContent = {
                Text(text = checkedState)
            }
        )
    }

    // }

}

