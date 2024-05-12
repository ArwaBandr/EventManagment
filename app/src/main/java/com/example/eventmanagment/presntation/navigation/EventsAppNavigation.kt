package com.example.eventmanagment.presntation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.eventmanagment.presntation.screens.auth.AuthViewModel
import com.example.eventmanagment.presntation.screens.auth.LoginScreen
import com.example.eventmanagment.presntation.screens.auth.SignUpScreen
import com.example.eventmanagment.presntation.screens.auth.SplashScreen

@Composable
fun EventAppNavigation(
    authViewModel: AuthViewModel,
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = authViewModel.isLoggedIn.value) {

        authNavigation(navController, authViewModel)
        mainAppNavigation(navController) {
            authViewModel.loguot()
        }

    }
}

fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    navigation(
        startDestination = Screens.Authentication.SplashScreen.rout,
        route = Screens.Authentication.rout
    ) {
        composable(Screens.Authentication.SplashScreen.rout) {
            SplashScreen(navController = navController)
        }
        composable(Screens.Authentication.SignUpScreen.rout) {
            SignUpScreen(navController = navController, viewModel = authViewModel)
        }
        composable(Screens.Authentication.LoginScreen.rout) {
            LoginScreen(navController = navController, viewModel = authViewModel)
        }
    }
}


fun NavGraphBuilder.mainAppNavigation(
    navController: NavHostController,
    logout: () -> Unit
) {
    navigation(startDestination = Screens.MainApp.Home.rout, route = Screens.MainApp.rout) {
        composable(Screens.MainApp.Home.rout) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            ) {

            }
        }
        composable(Screens.MainApp.TaskByDate.rout) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {

            }
        }
        composable(Screens.MainApp.CategoryScreen.rout) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Cyan)
            ) {
                Button(onClick = { logout.invoke() }) {
                    Text(text = "SignOut")
                }
            }}
            composable(Screens.MainApp.AddScreen.rout) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Magenta)
                ) {

                }
            }
        composable(Screens.MainApp.StaticsScreen.rout) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {

            }
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
        saveState=true
    }
}