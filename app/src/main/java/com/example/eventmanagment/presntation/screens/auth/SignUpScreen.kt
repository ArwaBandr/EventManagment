package com.example.eventmanagment.presntation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.eventmanagment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),

        ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 50.dp, top = 110.dp, bottom = 50.dp)
        )
        Column(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username by remember {
                mutableStateOf("")
            }
            var passowrd by remember {
                mutableStateOf("")
            }
            var emailId by remember {
                mutableStateOf("")
            }
            TextField(
                value = username, onValueChange = { username = it },
                label = {
                    Text(text = "Username", color = Color.Gray)
                },
                //shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user_login),
                        contentDescription = ""
                    )
                },
                modifier = Modifier
                     .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f)
                    .padding(10.dp),
                colors =TextFieldDefaults.textFieldColors(containerColor = Color.White)

            )

            TextField(
                value = emailId, onValueChange = { emailId = it },
                label = { Text(text = "Email", color = Color.Gray) },
               // shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password_icon),
                        contentDescription = ""
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                     .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                colors =TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )

            TextField(
                value = passowrd,
                onValueChange = { passowrd = it },
                label = { Text(text = "Password", style = TextStyle(color = Color.Gray)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password_icon),
                        contentDescription = "password icon"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 50.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors =TextFieldDefaults.textFieldColors(containerColor = Color.White)

            )
            Button(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .size(50.dp)
                    ,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

            ) {
                Text(text = "Create")
            }
            Text(
                text = "or with", modifier = Modifier
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
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "google icon",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally).clickable {  }
            )

        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun SignUpScreenPreview() {
//    SignUpScreen()
//}
