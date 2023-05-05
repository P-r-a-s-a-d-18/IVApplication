package com.example.iv1.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import android.util.Log
import com.example.iv1.R
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel
import java.time.format.TextStyle

@Composable
fun DisplayDiluentList(drug: Drug ) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(drug.diluent_list) {
                DiluentList(drug=drug, item = it)
            }
        }
    }
}


@Composable
fun DiluentList(
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

                Text(
                    text = "Storage : ",
                    color = Color.Blue,
                    fontSize = MaterialTheme.typography.h5.fontSize
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = drug.storage,
                    lineHeight = 30.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Diluent : ",
                    color = Color.Blue,
                    fontSize = MaterialTheme.typography.h5.fontSize
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = drug.iv_fluid,
                    lineHeight = 30.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}
