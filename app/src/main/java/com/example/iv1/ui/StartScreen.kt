package com.example.iv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.R
import com.example.iv1.ui.components.GradientButton

@Composable
fun StartScreen(
    onIRCalcButtonClicked: () -> Unit,
    onCompatibilityCheckButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    val gradient =
        Brush.horizontalGradient(listOf(Color(0xFFAFCAE9), Color(0xFF83AEF8)))
    LazyColumn() {
        items(1) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Image(
                    modifier = Modifier
                        .size(330.dp)
                        .clip(RectangleShape)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(30.dp)
                        ),
                    painter = painterResource(id = R.drawable.img1),
                    contentDescription = "Circular image"
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Welcome to NeoCheck",
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Serif,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = MaterialTheme.typography.h6.fontSize
                )

                Spacer(modifier = Modifier.height(20.dp))

                GradientButton(
                    text = "Check Compatibility",
                    gradient = gradient,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    onClick = { onCompatibilityCheckButtonClicked() }
                )

                Spacer(modifier = Modifier.height(10.dp))

                GradientButton(
                    text = "IR Calculator",
                    gradient = gradient,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    onClick = { onIRCalcButtonClicked() }
                )
            }
        }
    }
}