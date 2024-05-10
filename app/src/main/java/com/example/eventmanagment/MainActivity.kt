package com.example.eventmanagment

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eventmanagment.ui.theme.EventManagmentTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            EventManagmentTheme {
                Column {
                    var user by remember {
                        mutableStateOf((Firebase.auth.currentUser))
                    }
                    val luancher = rememberFirebaseAuthLauncher(onAuthComplete = { result ->
                        user = result.user

                    },
                        onAuthError = {
                            user = null
                            println(it.message)
                        })

                    val token =
                        "804885342105-9v7ar1kaj52bekomkgc2768qra923it7.apps.googleusercontent.com"
                    val context = LocalContext.current
                    Column {
                        if (user ==null){
                            Text(text = "Not Logged In")
                            Button(onClick = {
                                val gso =
                                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                        .requestIdToken(token)
                                        .requestEmail()
                                        .build()
                                val gooleSignInClint =GoogleSignIn.getClient(context,gso)
                                luancher.launch(gooleSignInClint.signInIntent)
                            }) {
                             Text(text = "Sign in via Google")
                            }
                        }else{
                            Text(text = "Welcom ${user?.displayName}")
                            
                            AsyncImage(model = user?.photoUrl, contentDescription = null,
                                Modifier
                                    .clip(CircleShape)
                                    .size(80.dp)
                            )
                            Button(onClick = { 
                                Firebase.auth.signOut()
                                user =null
                            }) {
                                Text(text = "Sign out")
                            }
                        }

                    }

                }


            }
        }
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
