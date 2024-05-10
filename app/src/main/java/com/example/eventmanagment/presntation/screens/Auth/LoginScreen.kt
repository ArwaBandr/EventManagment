package com.example.eventmanagment.presntation.screens.Auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Login",
            style = TextStyle(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(bottom = 50.dp)
                .align(Alignment.CenterHorizontally)
        )
        var userName by remember {
            mutableStateOf("")
        }
        var userPassword by remember {
            mutableStateOf("")
        }
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "Email ID or Username") },
            leadingIcon = {},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        TextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text(text = "Password") },
            leadingIcon = {},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
    Text(text = "forget password", modifier = Modifier.padding(start = 20.dp))

    Button(onClick = {}, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(0.5f)) {
        Text(text = "Login")
    }
}