package com.example.eventmanagment.presntation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val rout: String) {


    data object MainApp : Screens("mainGraph") {

        data object Home : Screens("home_screen")
        data object TaskByDate : Screens("task_by_screen")
        data object AddScreen : Screens("add_screen")
        data object CategoryScreen : Screens("category_screen")
        data object StaticsScreen : Screens("Statics_screen")
        data object DateDailog : Screens("DateDailog")

        data object TagDailog : Screens("TagDailog")
        data object LisOfTasksScreen : Screens("list_of_task_screen")
        data object DropDownMenu :Screens("drop_down_menu_screen")

        data object EditTaskScreen:Screens("edit_task_screen")

        data object  SettingsScreen :Screens("settings_screen")

    }

    data object Authentication : Screens("authGraph") {
        object SplashScreen : Screens(rout = "splash_screen")
        object LoginScreen : Screens(rout = "login_screen")
        object SignUpScreen : Screens(rout = "sign_up_screen")
    }
}

data class BottomNavigationItem(
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    fun bottomNavigationItem(): List<BottomNavigationItem> {

        return listOf(
            BottomNavigationItem(
                icon = Icons.Filled.Home,
                route = Screens.MainApp.Home.rout
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Search,
                route = Screens.MainApp.TaskByDate.rout
            ),
            BottomNavigationItem(
                icon = Icons.Filled.AddCircle,
                route = Screens.MainApp.AddScreen.rout
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Settings,
                route = Screens.MainApp.CategoryScreen.rout
            ),
            BottomNavigationItem(
                icon = Icons.Filled.DateRange,
                route = Screens.MainApp.StaticsScreen.rout
            ),
        )
    }

}
