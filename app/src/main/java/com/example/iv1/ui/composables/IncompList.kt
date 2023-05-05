package com.example.iv1.ui.composables

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iv1.IVScreen
import com.example.iv1.R
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun DisplayIncompList(drug: Drug, viewModel: DrugViewModel, navController: NavController) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(drug.incompatible_drugs) {
                ListIncompDrug(drug = drug, item = it, viewModel, navController)
            }
        }
    }
}

@Composable
fun ListIncompDrug(
    drug: Drug,
    item: String,
    viewModel: DrugViewModel,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
    ) {
        val result = viewModel.response.value
        Card(elevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    viewModel.getDrug2(drug1 = drug, drug2 = item.lowercase().trim())
                    navController.navigate(IVScreen.ResultDetails.name)
                }
        ) {

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.width(5.dp))

                Icon(
                    painter = painterResource(R.drawable.outline_cancel_24),
                    contentDescription ="Incompatible",
                    tint = Color.Red
                )

//                Image(
//                    modifier = Modifier.size(30.dp),
//                    painter = painterResource(id = R.drawable.drug2),
//                    contentDescription = "Drugs Logo"
//                )
                Spacer(modifier = Modifier.width(15.dp))

                Text(text = item, fontSize = MaterialTheme.typography.h6.fontSize, color = Color.Black)

                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
