package com.example.iv1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iv1.data.AuthViewModel
import com.example.iv1.ui.composables.LoginPage
import com.example.iv1.ui.composables.RegisterPage
import com.example.iv1.ui.theme.IV1Theme

class AuthenticationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IV1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (AuthViewModel().isUser().value) {
                        loginApp(LocalContext.current, LocalContext.current as? Activity)
                    } else {
                        LoginApplication()
                    }
                }
            }
        }
    }

    fun loginApp(context: Context, activity: Activity?) {
        val i = Intent(context, MainActivity2::class.java)
        activity?.finish()
        context.startActivity(i)
    }
}

@Composable
fun LoginApplication() {
    val navController = rememberNavController()
    val authModel = AuthViewModel()

    NavHost(navController = navController, startDestination = "login_page", builder = {
        composable("login_page", content = {
            LoginPage(
                navController = navController,
                authModel = authModel
            )
        })
        composable("register_page", content = {
            RegisterPage(
                navController = navController,
                authModel = authModel
            )
        })
    })
}
