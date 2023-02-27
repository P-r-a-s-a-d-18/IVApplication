package com.example.iv1.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel: ViewModel() {
    private var auth = Firebase.auth
    var flag = mutableStateOf(false)

    fun isUser(): MutableState<Boolean> {
        flag.value = auth.currentUser != null
        return flag
    }

    fun loginUser(email: String, password: String): MutableState<Boolean> {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                flag.value = task.isSuccessful
            }
        return flag
    }

    fun signupUser(email: String, password: String): MutableState<Boolean> {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                flag.value = task.isSuccessful
            }
        return flag
    }

    fun logoutUser() {
        auth.signOut()
    }
}