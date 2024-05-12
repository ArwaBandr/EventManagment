package com.example.eventmanagment.presntation.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eventmanagment.presntation.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val auth = Firebase.auth
    var isLoggedIn =
        if (auth.currentUser == null) mutableStateOf(Screens.Authentication.rout) else mutableStateOf(
            Screens.MainApp.rout
        )
val error = mutableStateOf("")
    fun login(userEmail: String, userPassword: String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                } else {
                    error.value = task.exception?.message.orEmpty()
                }

            }


    }

    fun loguot(){
        auth.signOut()
        isLoggedIn.value =Screens.Authentication.rout
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

