package com.example.eventmanagment.presntation.screens.task

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.example.eventmanagment.Translation
import com.example.eventmanagment.component.TaskHeaderView
import java.util.Locale

@Composable
fun SettingScreen(navController: NavHostController) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        TaskHeaderView(title = "Settings") {
//            navController.popBackStack()
//        }
val context = LocalContext.current
        val checkedState = remember {
            mutableStateOf("en")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = checkedState.value)
            Translation.SetLanguage(local = checkedState.value)
            Switch(
                checked = checkedState.value == "en",
                onCheckedChange = {
                    checkedState.value = if (it) "en" else "ar"
                    navController.popBackStack()

                },
                thumbContent = {
                    Text(text = checkedState.value)
                }
            )
        }

    // }

}

