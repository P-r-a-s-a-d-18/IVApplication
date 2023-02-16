package com.example.iv1.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.data.DrugViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResDetail(viewModel: DrugViewModel) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(10.dp))
    if (viewModel.getAssertion()) {
        LazyColumn {
            viewModel.getResultObject().forEach { obj ->
                val objId = viewModel.getResultObject().indexOf(obj)
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
                    if(viewModel.getResultObject().indexOf(it) == objId) {
                        DisplayData(it = it)
                    }
                }
            }
        }
    } else {
        DisplayMessage()
    }
  }
}
@Composable
fun DisplayData(it: HashMap<String, String>) {
    Spacer(modifier = Modifier.height(8.dp))

    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly) {
        Text(text = "Type", fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = it["Type"].toString(), fontSize = MaterialTheme.typography.h6.fontSize)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Effect", fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = it["Effect"].toString(), fontSize = MaterialTheme.typography.h6.fontSize)
    }
}

@Composable
fun DisplayMessage() {
    Text(modifier = Modifier.padding(10.dp),
        text = "No Information Available !!",
        style = TextStyle(fontSize = 20.sp,
        textAlign = TextAlign.Center))
}
