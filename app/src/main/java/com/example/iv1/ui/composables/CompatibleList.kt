package com.example.iv1.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.iv1.R
import com.example.iv1.data.Drug

@Composable
fun DisplayCompList(drug: Drug) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(drug.compatible_drugs) {
                ListCompDrug(item = it)
            }
        }
    }
}

@Composable
fun ListCompDrug(
    item: String
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
//                .clickable {
//                    viewModel.setDrug(drug)
//                    onListItemClicked()
//                }
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
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
