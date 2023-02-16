package com.example.iv1.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
    onElementClicked: (Pair<Drug, Drug>) -> Unit
) {
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

@Composable
fun DisplayItem(
    pair: Pair<Drug, Drug>,
    onElementClicked: (Pair<Drug, Drug>) -> Unit,
    viewModel: DrugViewModel
){
    Spacer(modifier = Modifier.height(17.dp))

    Column(modifier = Modifier.padding(10.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(vertical = 7.dp)
            .clickable {
                onElementClicked(pair)
                viewModel.setPair(pair)
            }
        ) {
            Card(
                elevation = 10.dp,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                border = BorderStroke(1.dp,Color.LightGray.copy(alpha = ContentAlpha.medium)),
                backgroundColor = Color.LightGray
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(),
                       // .padding(7.dp)
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(0.1.dp))
                    Text(
                        text = pair.first.drug_name + " -- " + pair.second.drug_name,
                        fontSize = MaterialTheme.typography.h5.fontSize
                    )
                    Text(text = " > ", fontSize = MaterialTheme.typography.h5.fontSize)
                }
            }
        }
    }
}

