package com.example.eventmanagment.presntation.screens.auth

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eventmanagment.presntation.navigation.Screens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
     val auth = Firebase.auth
    var isLoggedIn =
        if (auth.currentUser == null) mutableStateOf(Screens.Authentication.rout) else mutableStateOf(
            Screens.MainApp.rout
        )
val error = mutableStateOf("")

    init {
        auth.apply {
            this.addAuthStateListener {
                isLoggedIn.value =
                    if (it.currentUser == null) Screens.Authentication.rout else Screens.MainApp.rout

            }
        }
    }

    fun login(userEmail: String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                } else {
                    error.value = task.exception?.message.orEmpty()
                }

            }


    }

    fun loguot(context:Context){
        auth.signOut()
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()).signOut()

    }

    fun signup(email:String ,password:String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if (task.isSuccessful) {
                } else {
                    error.value = task.exception?.message.orEmpty()
                }

            }

    }

  fun resetPassword(email: String) {
      auth.sendPasswordResetEmail(email)
          .addOnCompleteListener { task ->
              if (task.isSuccessful) {
              } else {
                  error.value = task.exception?.message.orEmpty()
              }
          }


  }


}

