package com.example.eventmanagment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventmanagment.component.BottomBar
import com.example.eventmanagment.presntation.navigation.EventAppNavigation
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.presntation.screens.auth.AuthViewModel
import com.example.eventmanagment.ui.theme.EventManagmentTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val authviewModel :AuthViewModel= hiltViewModel()
            EventManagmentTheme {
                val navController = rememberNavController()

                var showBottomBar by rememberSaveable {
                    mutableStateOf(false)
                }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                showBottomBar = when (navBackStackEntry?.destination?.route) {
                    Screens.MainApp.Home.rout -> true
                    Screens.MainApp.AddScreen.rout -> true
                    Screens.MainApp.TaskByDate.rout -> true
                    Screens.MainApp.CategoryScreen.rout -> true
                    Screens.MainApp.StaticsScreen.rout -> true
                    else -> false
                }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { contentDescription = "MyScreen" },
                ) { paddingvalues ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(paddingvalues)
                    ) {
                        if (authviewModel.error.value.isNotEmpty()) {
                            Snackbar(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize(), containerColor = Color.Red.copy(0.5f)
                            )
                            {
                                Text(text = authviewModel.error.value)
                            }
                        }
                        EventAppNavigation(authViewModel = authviewModel ,navController)
                    }
                        if (showBottomBar) {
                            BottomBar(navController)
                        }

                }
            }
        }
    }
}