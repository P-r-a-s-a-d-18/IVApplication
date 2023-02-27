package com.example.iv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iv1.R
import com.example.iv1.data.AuthViewModel

@Composable
fun LoginPage(
    navController: NavController,
    authModel: AuthViewModel
) {
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }

    val icon = if(passwordVisibility.value) {
        painterResource(id = R.drawable.visibility_on)
    } else {
        painterResource(id = R.drawable.visibility_off)
    }

    Spacer(modifier = Modifier.height(20.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
            )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter),
        ) {

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Sign In",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = TextUnit.Unspecified,
                        fontSize = 17.sp
                    )

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "emailValue",
                            tint = Color.Gray
                        )
                    }
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    label = { Text(text = "Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "passwordValue",
                            tint = Color.Gray
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = icon,
                                contentDescription = "",
                                tint = Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                )


                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    onClick = {
                        if (authModel.loginUser(emailValue.value, passwordValue.value).value) {
                            navController.navigate("main_app")
                        } else {
                            /* TODO: */
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text(text = "Login", fontSize = TextUnit.Companion.Unspecified)
                }

                Spacer(modifier = Modifier.padding(10.dp))

                TextButton(onClick = {
                    navController.navigate("register_page") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }

                }) {
                    Text(
                        text = "Create An Account",
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Spacer(modifier = Modifier.padding(20.dp))

            }
        }
    }
}
