package com.example.eventmanagment.presntation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
 import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventmanagment.R
import com.example.eventmanagment.component.LoginWithGoogle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel) {
    Text(
        text = stringResource(id = R.string.login),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(vertical = 50.dp, horizontal = 16.dp)

    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {

        var userEmail by remember {
            mutableStateOf("")
        }
        var userPassword by remember {
            mutableStateOf("")
        }

        TextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = {
                Text(
                    text = stringResource(id = R.string.email_or_username),
                    style = TextStyle(color = Color.Gray),
                    textAlign = TextAlign.Start
                )
            },
keyboardOptions= KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.user_login),
                    contentDescription = "login icon "
                )
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 80.dp)
                .fillMaxWidth(0.90f)
                .background(Color.White),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White, focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray)
        )
        TextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text(text = stringResource(id =R.string.password ), style = TextStyle(color = Color.Gray)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password_icon),
                    contentDescription = "password icon"
                )
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .fillMaxWidth(0.90f)
                .background(Color.White),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White,focusedTextColor = Color.Black, unfocusedTextColor = Color.Gray)

        )

        Text(text = stringResource(id = R.string.forget_passowrd), style = TextStyle(Color.Blue), modifier = Modifier
            .padding(start = 230.dp, bottom = 80.dp)
            .clickable {
                if (userEmail.isNotEmpty()) {
                    viewModel.resetPassword(userEmail)
                }
            })
val context = LocalContext.current
        Button(
            onClick = {
                if (userEmail.isNotEmpty() && userPassword.isNotEmpty()){
             viewModel.login(userEmail,userPassword)
                }
                else{
                    Toast.makeText(context,"Please fill your email or password", Toast.LENGTH_SHORT).show()}

                }
            ,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
                .size(50.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

        ) {
            Text(text = stringResource(id = R.string.login), color = Color.White)
        }
        Text(
            text = stringResource(id = R.string.or_with), modifier = Modifier
                .padding(top = 50.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray
        )
        Divider(
            modifier = Modifier
                // .padding(top = 50.dp)
                .fillMaxWidth(0.70f)
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.primaryContainer
        )
        LoginWithGoogle(navController)
//        Icon(
//            painter = painterResource(id = R.drawable.google_icon),
//            contentDescription = "google icon",
//            tint = Color.Unspecified,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .clickable {  }
//        )

    }

}

//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun LoginScreenPreview() {
//    LoginScreen(navController)
//}