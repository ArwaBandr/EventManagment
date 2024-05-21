package com.example.eventmanagment.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.eventmanagment.MainActivity
import com.example.eventmanagment.component.BottomBar
import com.example.eventmanagment.presntation.navigation.EventAppNavigation
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.presntation.screens.auth.AuthViewModel
import com.example.eventmanagment.ui.theme.EventManagmentTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TstTapNavigation {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var authViewModel: AuthViewModel
    private lateinit var navController: TestNavHostController


    @Before
    fun init() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            authViewModel = hiltViewModel()
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(DialogNavigator())
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            EventManagmentTheme {
                EventAppNavigation(authViewModel = authViewModel, navController = navController)
                BottomBar(navController = navController)
            }
        }
    }

    @Test
    fun testBottomBarNavigation(){

        composeTestRule.onNodeWithTag("Navigate To Screen").assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.AddScreen.rout)

        composeTestRule.onNodeWithTag(Screens.MainApp.TaskByDate.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.TaskByDate.rout)

        composeTestRule.onNodeWithTag(Screens.MainApp.Home.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.Home.rout)


        composeTestRule.onNodeWithTag(Screens.MainApp.CategoryScreen.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.CategoryScreen.rout)

        composeTestRule.onNodeWithTag(Screens.MainApp.StaticsScreen.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.StaticsScreen.rout)

    }
}

fun NavController.assertCurrentRouteName(expecedRout:String){
    TestCase.assertEquals(expecedRout,currentBackStackEntry?.destination?.route)
}