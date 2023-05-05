package com.example.iv1.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun DisplayCompList(drug: Drug, viewModel: DrugViewModel) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(drug.compatible_drugs) {
                ListCompDrug(drug, item = it)
            }
        }
    }
}

@Composable
fun ListCompDrug(
    drug: Drug,
    item: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
    ) {
        Card(elevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                }
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.width(5.dp))

                Icon(imageVector = Icons.Default.Check, contentDescription = "Compatible",
                    tint=Color.Green,
                    modifier = Modifier.size(30.dp))

                Spacer(modifier = Modifier.width(15.dp))

                Text(text = item, fontSize = MaterialTheme.typography.h6.fontSize, color = Color.Black)

                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
