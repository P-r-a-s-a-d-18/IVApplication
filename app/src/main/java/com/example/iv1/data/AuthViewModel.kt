package com.example.iv1.data

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
}