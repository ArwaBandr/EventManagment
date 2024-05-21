package com.example.eventmanagment.presntation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.eventmanagment.component.DatePickerr
import com.example.eventmanagment.component.DropDownMenu
import com.example.eventmanagment.presntation.screens.auth.AuthViewModel
import com.example.eventmanagment.presntation.screens.auth.LoginScreen
import com.example.eventmanagment.presntation.screens.auth.SignUpScreen
import com.example.eventmanagment.presntation.screens.auth.SplashScreen
import com.example.eventmanagment.presntation.screens.task.AddNewTag
import com.example.eventmanagment.presntation.screens.task.AddTaskScreen
import com.example.eventmanagment.presntation.screens.task.AddTaskViewModel
import com.example.eventmanagment.presntation.screens.task.FilterTasksViewModel
import com.example.eventmanagment.presntation.screens.task.HomeScreen
import com.example.eventmanagment.presntation.screens.task.ListOfTasksScreen
import com.example.eventmanagment.presntation.screens.task.TaskByDatScreen
import com.google.firebase.auth.FirebaseUser

@Composable
fun EventAppNavigation(
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = authViewModel.isLoggedIn.value) {
        authNavigation(navController, authViewModel)
        mainAppNavigation(navController, { authViewModel.loguot(context) }) {
            authViewModel.auth.currentUser
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
    logout: () -> Unit,
    firbaseUser: () -> FirebaseUser?
) {
    navigation(startDestination = Screens.MainApp.Home.rout, route = Screens.MainApp.rout) {
        composable(Screens.MainApp.Home.rout) {
            val viewModel = hiltViewModel<FilterTasksViewModel>()
            HomeScreen(firbaseUser.invoke(), viewModel, navController)
        }
        composable(Screens.MainApp.TaskByDate.rout) {
            val viewModel = hiltViewModel<FilterTasksViewModel>()
            TaskByDatScreen(viewModel,navController)

//            val viewModel: TaskViewModel = hiltViewModel()
//            LazyColumn(
//                Modifier
//                    .fillMaxSize()
//                    .background(Color.Gray)
//            ) {
//                item {
//                    val result = viewModel.tasks.collectAsState(initial = null)
//                    val tags = viewModel.tags.collectAsState(initial = null)
//                    val tasksByTags = viewModel.taskByTags.collectAsState(initial = null)
//                    Text(text = result.value.toString())
//                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
//                    Text(text = tags.value.toString())
//                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
//                    Text(text = tasksByTags.value.toString())
//                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
//
//
//                }
//            }
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
            }
        }
        composable(Screens.MainApp.AddScreen.rout) {
            val viewmodel: AddTaskViewModel = hiltViewModel()
            viewmodel.taskDate.value = it.savedStateHandle.get<String>("selectedDate").orEmpty()
            AddTaskScreen(navController, viewmodel)
        }
        composable(Screens.MainApp.StaticsScreen.rout) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {

            }
        }
        dialog(
            Screens.MainApp.DateDailog.rout, dialogProperties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            // MonthlyHorizentalCalenderView(navController)
            DatePickerr(navController) {
                navController.popBackStack()
            }
        }
        dialog(
            Screens.MainApp.TagDailog.rout, dialogProperties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        ) {
            val viewModel = hiltViewModel<AddTaskViewModel>()
            AddNewTag(addTaskViewModel = viewModel, navController = navController)

        }
        composable(
            "${Screens.MainApp.LisOfTasksScreen.rout}/{title}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
            })
        ) {
            val viewModel = hiltViewModel<FilterTasksViewModel>()
            ListOfTasksScreen(
                title = it.arguments?.getString("title").orEmpty(),
                filterTasksViewModel = viewModel,
                navController = navController
            )
        }
        dialog(Screens.MainApp.DropDownMenu.rout,dialogProperties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true) ){
           DropDownMenu(navController)
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive = true
        saveState = true
    }
}