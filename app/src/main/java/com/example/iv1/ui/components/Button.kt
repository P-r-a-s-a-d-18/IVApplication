package com.example.iv1.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Button1(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = Modifier
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text, fontSize = 16.sp, color = Color.White)
        }
    }
}