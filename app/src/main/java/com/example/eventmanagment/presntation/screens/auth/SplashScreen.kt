package com.example.eventmanagment.presntation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.eventmanagment.R
import com.example.eventmanagment.presntation.navigation.Screens

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().semantics {
                                                    testTag="splashScreen"
        },
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(painter = painterResource(id = R.drawable.first_image), contentDescription = "", modifier = Modifier.semantics {
            testTag="intro image"
        })
        Text(
            text = "Event App",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.semantics {
                contentDescription="title text"
            }
        )
        Text(
            text = "plan what will you will do to be organized for today,tomorrow and beyond.",
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            ).semantics {
                contentDescription="description text"
            }
        )
        Button(
            onClick = {navController.navigate(Screens.Authentication.LoginScreen.rout)},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
                .size(50.dp)
                .semantics {
                           testTag="login Button"
                },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Login", color = Color.White)
        }
        Text(
            text = "Sign in",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {navController.navigate(Screens.Authentication.SignUpScreen.rout) }
                .align(Alignment.CenterHorizontally)
                .semantics {
                testTag="sign in"
            }
        )
    }
}

//@Composable
//@Preview(showBackground = true)
//fun SplashScreenPreview() {
//    SplashScreen()
//}