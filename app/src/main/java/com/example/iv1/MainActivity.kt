package com.example.iv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iv1.data.AuthViewModel
import com.example.iv1.ui.LoginPage
import com.example.iv1.ui.RegisterPage
import com.example.iv1.ui.theme.IV1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            IV1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (AuthViewModel().isUser().value) {
                        Start()
                    } else {
                        LoginApplication()
                    }
                }
            }
        }
    }

    @Composable
    fun LoginApplication(){
        val navController = rememberNavController()
        val authModel = AuthViewModel()

        NavHost(navController = navController, startDestination = "login_page", builder = {
            composable("login_page", content = { LoginPage(
                navController = navController,
                authModel = authModel
            ) })
            composable("register_page", content = { RegisterPage(
                navController = navController,
                authModel = authModel
            ) })
            composable("main_app", content = { Start() })
        })
    }
}
