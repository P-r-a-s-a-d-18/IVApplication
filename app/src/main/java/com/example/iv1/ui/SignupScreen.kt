package com.example.iv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun RegisterPage(
    navController: NavController,
    authModel: AuthViewModel
) {
    var auth = Firebase.auth
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    var passwordVisibility1 = remember { mutableStateOf(false) }
    var passwordVisibility2 = remember { mutableStateOf(false) }

    val icon1 = if(passwordVisibility1.value) {
        painterResource(id = R.drawable.visibility_on)
    } else {
        painterResource(id = R.drawable.visibility_off)
    }

    val icon2 = if(passwordVisibility2.value) {
        painterResource(id = R.drawable.visibility_on)
    } else {
        painterResource(id = R.drawable.visibility_off)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.signup), contentDescription = null,
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.padding(15.dp))

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.70f)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(50.dp))

                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = TextUnit.Unspecified,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.padding(5.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                OutlinedTextField(
//                    value = nameValue.value,
//                    onValueChange = { nameValue.value = it },
//                    label = { Text(text = "Name") },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth(0.8f)
//                )

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

                    Spacer(modifier = Modifier.height(10.dp))

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
                                passwordVisibility1.value = !passwordVisibility1.value
                            }) {
                                Icon(
                                    painter = icon1,
                                    contentDescription = "",
                                    tint = Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility1.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = confirmPasswordValue.value,
                        onValueChange = { confirmPasswordValue.value = it },
                        label = { Text(text = "Confirm Password") },
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
                                passwordVisibility2.value = !passwordVisibility2.value
                            }) {
                                Icon(
                                    painter = icon2,
                                    contentDescription = "",
                                    tint = Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility2.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(
                        onClick = {
                            if (authModel.signupUser(emailValue.value, passwordValue.value).value) {
                                navController.navigate("login_page") {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            } else {
                                /* TODO: */
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign Up", fontSize = TextUnit.Companion.Unspecified)
                    }

                    Spacer(modifier = Modifier.padding(15.dp))

                    Text(
                        text = "Login Instead",
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.subtitle2,
                        color=Color.Blue,
                        modifier = Modifier.clickable(onClick = {
                            navController.navigate("login_page") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        })
                    )
                    Spacer(modifier = Modifier.padding(20.dp))

                }
            }
        }
    }
}
