package com.example.iv1.data

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.iv1.AuthenticationActivity
import com.example.iv1.MainActivity2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AuthViewModel: ViewModel() {
    private var auth = Firebase.auth
    var flag = mutableStateOf(false)

    fun isUser(): MutableState<Boolean> {
        val user = FirebaseAuth.getInstance().currentUser
        flag.value = user != null
        return flag
    }

    fun loginUser(email: String, password: String, context: Context) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    AuthenticationActivity().loginApp(context, context as? Activity)
                } else {
                    Toast.makeText(context, "Login Unsuccessful !! Please retry !!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signupUser(email: String, password: String, navController: NavController, context: Context) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate("login_page") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                } else {
                    Toast.makeText(context, "Couldn't create user !! Please retry !!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun logoutUser(context: Context, activity: Activity?) {
        FirebaseAuth.getInstance().signOut()
        MainActivity2().signOutApp(context, activity)
    }
}