package com.example.iv1.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.iv1.data.DrugViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResDetail(viewModel: DrugViewModel) {
    LazyColumn {
        viewModel.getResultObject().forEach { obj ->
            stickyHeader {
                obj["Mixture"]?.let {
                    Text(
                        text = it,
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                }
            }
            items(viewModel.getResultObject()) { it ->
                DisplayData(it = it)
            }
        }
    }
}

@Composable
fun DisplayData(it: HashMap<String, String>) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = "Type", fontSize = MaterialTheme.typography.h5.fontSize)
    Text(text = it["Type"].toString(), fontSize = MaterialTheme.typography.h6.fontSize)
    Spacer(modifier = Modifier.height(5.dp))
    Text(text = "Effect", fontSize = MaterialTheme.typography.h5.fontSize)
    Text(text = it["Effect"].toString(), fontSize = MaterialTheme.typography.h6.fontSize)
}
