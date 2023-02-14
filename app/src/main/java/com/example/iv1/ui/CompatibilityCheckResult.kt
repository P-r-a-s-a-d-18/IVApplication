package com.example.iv1.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel


@Composable
fun GetCheck(
    viewModel: DrugViewModel,
    onElementClicked: (Pair<Drug, Drug>) -> Unit
) {
    if (viewModel.tempList.isEmpty() || viewModel.tempList.size == 1) {
        Text(text = "Not enough drugs selected to check compatibility !!", fontSize = MaterialTheme.typography.h5.fontSize)
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
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(18.dp)
        .height(50.dp)
        .clickable {
            onElementClicked(pair)
            viewModel.setPair(pair)
        }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = pair.first.drug_name + " -- " + pair.second.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)
            Text(text = " > ", fontSize = MaterialTheme.typography.h5.fontSize)
        }
    }
}
