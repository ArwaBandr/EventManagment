package com.example.eventmanagment.component

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eventmanagment.R
import com.example.eventmanagment.presntation.navigation.Screens
import com.example.eventmanagment.presntation.screens.auth.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun LoginWithGoogle(navController: NavController) {

    val authviewModel = hiltViewModel<AuthViewModel>()
    var user by remember {
        mutableStateOf((Firebase.auth.currentUser))
    }
    val luancher = rememberFirebaseAuthLauncher(onAuthComplete = { result ->
        user = result.user
        authviewModel.isLoggedIn.value = Screens.MainApp.rout

    },
        onAuthError = {
            user = null
            authviewModel.isLoggedIn.value = Screens.Authentication.rout
        })

    val token =
        "804885342105-9v7ar1kaj52bekomkgc2768qra923it7.apps.googleusercontent.com"
    val context = LocalContext.current
    if (user == null) {
        authviewModel.isLoggedIn.value = Screens.Authentication.rout
        Image(modifier = Modifier
            .padding(4.dp)
            .clickable {
                val gso =
                    GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                val gooleSignInClint = GoogleSignIn.getClient(context, gso)
                luancher.launch(gooleSignInClint.signInIntent)
            },painter =painterResource(id = R.drawable.google_icon) , contentDescription = "google")
    } else {
        authviewModel.isLoggedIn.value = Screens.MainApp.rout
        navController.navigate(Screens.MainApp.Home.rout)
    }

}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult((ApiException::class.java))
            var credntail = GoogleAuthProvider.getCredential(account.idToken, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credntail).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }

    }
}