package com.example.eventmanagment.presntation.navigation

sealed class Screens(val rout:String) {
    object SplashScreen :Screens(rout ="splash_screen")
    object LoginScreen :Screens(rout = "login_screen")
    object SignUpScreen:Screens(rout = "sign_up_screen")
}