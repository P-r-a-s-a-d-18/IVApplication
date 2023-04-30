package com.example.iv1.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun GetCheck(
    viewModel: DrugViewModel,
    onElementClicked: (Pair<Drug, Drug>) -> Unit,
//    onBackBtnClicked: () -> Unit
) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize()
    )
    {
        if (viewModel.tempList.isEmpty() || viewModel.tempList.size == 1) {
            Text(
                modifier = Modifier.padding(15.dp),
                fontFamily = FontFamily.Serif,
                lineHeight = 30.sp,
                text = "Not enough drugs selected to check compatibility.",
                style = TextStyle(fontSize = 20.sp)
            )
        } else {
            LazyColumn {
                items(viewModel.getToCheck()) { pair ->
                    DisplayItem(pair, onElementClicked, viewModel)
                }
            }
        }
    }
//    Row(modifier = Modifier.fillMaxSize().padding(bottom = 5.dp),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.Bottom
//    ) {
//        Button(
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color.Blue
//            ),
//            onClick = { onBackBtnClicked() }, modifier = Modifier
//                .width(110.dp)
//                .height(40.dp)
//        ) {
//            Text(text = "Home", color = Color.White, style = TextStyle(fontSize = 18.sp))
//        }
//    }
}

@Composable
fun DisplayItem(
    pair: Pair<Drug, Drug>,
    onElementClicked: (Pair<Drug, Drug>) -> Unit,
    viewModel: DrugViewModel
){
    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = Modifier.padding(vertical = 1.dp, horizontal = 10.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(vertical = 5.dp)
            .clickable {
                onElementClicked(pair)
                viewModel.setPair(pair.first, pair.second)
            }
        ) {
            Card(
                elevation = 10.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                border = BorderStroke(1.dp,Color.LightGray.copy(alpha = ContentAlpha.medium))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
//                        .background(
//                            brush = Brush.linearGradient(
//                                colors = listOf(
//                                    color_b,
//                                    color_g
//                                )
//                            )
//                        )
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        text = pair.first.drug_name + " with " + pair.second.drug_name,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(0.3f))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next Arrow",
                    tint = Color.Black)
                    Spacer(modifier = Modifier.width(0.2.dp))
                }
            }
        }
    }
}

