package com.example.iv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {


        Box(
            modifier = Modifier
                /*.background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                )*/
                .align(Alignment.BottomCenter),
        ) {

            Image(
                painter = painterResource(id = R.drawable.user_sign_in),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),

                )
            Column(
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                ,

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //.........................Spacer
                Spacer(modifier = Modifier.height(50.dp))

                //.........................Text: title
                androidx.compose.material.Text(
                    text = "Sign In",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 130.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary,
                )

                OutlinedTextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it },
                    label = { Text(text = "Email Address") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    label = { Text(text = "Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                )

                val gradientColor = listOf(Color(0xFF484BF1), Color(0xFF673AB7))
                val cornerRadius = 16.dp


                Spacer(modifier = Modifier.padding(10.dp))

                Button(onClick = { authModel.logIn(emailValue.toString(), passwordValue.toString()) }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)) {
                    Text(text = "Sign Up", fontSize = TextUnit.Companion.Unspecified)
                }

                Spacer(modifier = Modifier.padding(10.dp))
                androidx.compose.material.TextButton(onClick = {

                    navController.navigate("register_page"){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }

                }) {
                    androidx.compose.material.Text(
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


//...........................................................................
//@Composable
//private fun GradientButton(
//    gradientColors: List<Color>,
//    cornerRadius: Dp,
//    nameButton: String,
//    roundedCornerShape: RoundedCornerShape,
//    authModel: AuthViewModel
//) {
//
//    androidx.compose.material.Button(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 32.dp, end = 32.dp),
//        onClick = {
//            /* TODO: */
//        },
//
//        contentPadding = PaddingValues(),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color.Transparent
//        ),
//        shape = RoundedCornerShape(cornerRadius)
//    ) {
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    brush = Brush.horizontalGradient(colors = gradientColors),
//                    shape = roundedCornerShape
//                )
//                .clip(roundedCornerShape)
//                /*.background(
//                    brush = Brush.linearGradient(colors = gradientColors),
//                    shape = RoundedCornerShape(cornerRadius)
//                )*/
//                .padding(horizontal = 16.dp, vertical = 8.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            androidx.compose.material.Text(
//                text = nameButton,
//                fontSize = 20.sp,
//                color = Color.White
//            )
//        }
//    }
//}
//
//
////email id
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun SimpleOutlinedTextFieldSample() {
//    val keyboardController = LocalSoftwareKeyboardController.current
//    var text by rememberSaveable { mutableStateOf("") }
//
//    OutlinedTextField(
//        value = text,
//        onValueChange = { text = it },
//        shape = RoundedCornerShape(topEnd =12.dp, bottomStart =12.dp),
//        label = {
//            Text("Name or Email Address",
//                color = MaterialTheme.colors.primary,
//                style = MaterialTheme.typography.body1,
//            ) },
//        keyboardOptions = KeyboardOptions(
//            imeAction = ImeAction.Next,
//            keyboardType = KeyboardType.Email
//        ),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = MaterialTheme.colors.primary,
//            unfocusedBorderColor = MaterialTheme.colors.primary),
//        singleLine = true,
//        modifier = Modifier.fillMaxWidth(0.8f),
//        keyboardActions = KeyboardActions(
//            onDone = {
//                keyboardController?.hide()
//                // do something here
//            }
//        )
//
//    )
//}
//
////password
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun SimpleOutlinedPasswordTextField() {
//    val keyboardController = LocalSoftwareKeyboardController.current
//    var password by rememberSaveable { mutableStateOf("") }
//    var passwordHidden by rememberSaveable { mutableStateOf(true) }
//    OutlinedTextField(
//        value = password,
//        onValueChange = { password = it },
//        shape = RoundedCornerShape(topEnd =12.dp, bottomStart =12.dp),
//        label = {
//            Text("Enter Password",
//                color = MaterialTheme.colors.primary,
//                style = MaterialTheme.typography.body1,
//            ) },
//        visualTransformation =
//        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
//        //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//        keyboardOptions = KeyboardOptions(
//            imeAction = ImeAction.Done,
//            keyboardType = KeyboardType.Password
//        ),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = MaterialTheme.colors.primary,
//            unfocusedBorderColor = MaterialTheme.colors.primary),
//        modifier = Modifier.fillMaxWidth(0.8f),
//        keyboardActions = KeyboardActions(
//            onDone = {
//                keyboardController?.hide()
//                // do something here
//            }
//        )
//    )
//}