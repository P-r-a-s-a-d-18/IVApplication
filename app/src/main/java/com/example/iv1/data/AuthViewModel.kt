package com.example.iv1.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel: ViewModel() {
    private var auth = Firebase.auth
    var flag = mutableStateOf(false)

    fun isUser(): Boolean {
        if (auth.currentUser != null) {
            return true
        }
        return false
    }

    fun signUp(email: String, password: String): MutableState<Boolean> {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    /* TODO: */
                    Log.d(TAG, "createUserWithEmail:success")
                    flag.value = true
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                }
            }
        return flag
    }

    fun logIn(email: String, password: String): MutableState<Boolean> {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    /* TODO: */
                    Log.d(TAG, "signInWithEmail:success")
                    flag.value = true
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        return flag
    }
}