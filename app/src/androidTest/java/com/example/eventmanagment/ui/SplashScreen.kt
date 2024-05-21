package com.example.eventmanagment.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.eventmanagment.presntation.screens.auth.SplashScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SplashScreen {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun init() {
        hiltRule.inject()
        composeRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(DialogNavigator())
                navigatorProvider.addNavigator(ComposeNavigator())

            }
            SplashScreen(navController = navController)
        }
    }
    @Test
    fun testSplashScreen(){
        composeRule.onNodeWithTag("splashScreen").assertIsDisplayed()
    }
    @Test
    fun testSplashScreenContent(){
        composeRule.onNodeWithTag("intro image").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("title text").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("description text").assertIsDisplayed()
    }

    @Test
    fun testSplashScreenClickButton(){
        composeRule.onNodeWithTag("login Button").assertHasClickAction()
        composeRule.onNodeWithTag("sign in").assertHasClickAction()
    }

}