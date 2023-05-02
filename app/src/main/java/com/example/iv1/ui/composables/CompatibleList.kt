package com.example.iv1.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iv1.R
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
            .height(80.dp)
            .padding(10.dp)
    ) {
        Card(elevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                }
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.width(5.dp))

                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.drug2),
                    contentDescription = "Drugs Logo"
                )
                Spacer(modifier = Modifier.width(15.dp))

                Text(text = item, fontSize = MaterialTheme.typography.h6.fontSize, color = Color.Black)

                Spacer(modifier = Modifier.weight(1f))

                Icon(painter = painterResource(R.drawable.baseline_check_24), contentDescription ="Compatible", tint=Color.Green)
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
