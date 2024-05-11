package com.example.eventmanagment.presntation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventmanagment.presntation.screens.auth.LoginScreen
import com.example.eventmanagment.presntation.screens.auth.SignUpScreen
import com.example.eventmanagment.presntation.screens.auth.SplashScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()){
     NavHost(navController = navController, startDestination = Screens.SplashScreen.rout ){
         composable(Screens.SplashScreen.rout){
             SplashScreen(navController)
         }
         composable(Screens.LoginScreen.rout){
             LoginScreen(navController)
         }
         composable(Screens.SignUpScreen.rout){
             SignUpScreen(navController)
         }

     }
}