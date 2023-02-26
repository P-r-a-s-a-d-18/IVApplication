package com.example.iv1.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthViewModel: ViewModel() {
    private var auth = Firebase.auth

    fun isUser(): Boolean {
        if (auth.currentUser != null) {
            return true
        }
        return false
    }

    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    /* TODO: */
                    Log.d(TAG, "createUserWithEmail:success")
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                }
            }
    }

    fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    /* TODO: */
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
    }
}