package com.example.iv1.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.R
import com.example.iv1.data.DrugViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResDetail(viewModel: DrugViewModel) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(5.dp))
    if (viewModel.getAssertion()) {
        LazyColumn {
            viewModel.getResultObject()?.forEach { obj ->
                val objId = viewModel.getResultObject()!!.indexOf(obj)
        //                stickyHeader {
        //                    obj["Mixture"]?.let {
        //                        Text(
        //                            text = it,
        //                            color = Color.White,
        //                            modifier = Modifier
        //                                .background(Color.Gray)
        //                                .padding(8.dp)
        //                                .fillMaxWidth(),
        //                            style = TextStyle(fontSize = 18.sp))
        //                    }
        //                }
                items(viewModel.getResultObject()!!) { it ->
                    if(viewModel.getResultObject()!!.indexOf(it) == objId) {
                        DisplayData(it = it)
                    }
                }
            }
        }
    } else {
        Icon(painter = painterResource(R.drawable.outline_warning_24), contentDescription ="Compatible", tint=Color.Yellow)
        DisplayMessage("!! NOT TESTED !!")
    }
  }
}
@Composable
fun DisplayData(it: HashMap<String, String>) {
    Card(
//        elevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Concentration",
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
            )
            Divider(thickness = 2.dp)
            Text(
                text = it["dose"].toString(),
                fontSize = MaterialTheme.typography.body1.fontSize
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Result",
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )
            Divider(thickness = 2.dp)
            Text(
                text = it["type"].toString(),
                fontSize = MaterialTheme.typography.body1.fontSize
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Details",
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )
            Divider(thickness = 2.dp)
            Text(
                text = it["reason"].toString(),
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
    Divider(thickness = 2.dp, color = Color.Black)
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun DisplayMessage(msg: String) {
    Text(modifier = Modifier.padding(10.dp),
        text = msg,
        style = TextStyle(fontSize = 20.sp,
        textAlign = TextAlign.Center))
}
