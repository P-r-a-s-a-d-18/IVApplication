package com.example.iv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.R
import com.example.iv1.data.DataState
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun SetData(
    viewModel: DrugViewModel,
    onDoneBtnClicked: (ArrayList<Drug>) -> Unit,
    onListItemClicked: () -> Unit
) {
    when(val result = viewModel.response.value) {
        is DataState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            ShowDrugList(result.data, onDoneBtnClicked = onDoneBtnClicked, onListItemClicked = onListItemClicked ,viewModel)
        }
        is DataState.Failure -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = result.message, fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
        else -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error fetching data !!", fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
    }
}

@Composable
fun ShowDrugList(
    drugs: ArrayList<Drug>,
    onDoneBtnClicked: (ArrayList<Drug>) -> Unit,
    onListItemClicked: () -> Unit,
    viewModel: DrugViewModel
) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize(0.93f)) {
        SearchBar()

        LazyColumn {
            items(drugs) { drug ->
                ListItem(drug, onListItemClicked, viewModel)
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom)
    {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue
            ),
            onClick = { onDoneBtnClicked(drugs) }, modifier = Modifier
                .width(110.dp)
                .height(47.dp)
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(text = "Next", color = Color.White, style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(2.dp),
            verticalAlignment =Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(0.1.dp))
            Text(
                text = "Search",style = TextStyle(fontSize = 20.sp),color = Color.Gray, textAlign = TextAlign.Start,

                )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")

            }
        }
    }
}

@Composable
fun ListItem(
    drug: Drug,
    onListItemClicked: () -> Unit,
    viewModel: DrugViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
    ) {
        Card(elevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    viewModel.setDrug(drug)
                    onListItemClicked()
                }
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                verticalAlignment =Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(0.1.dp))

                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.drug2),
                    contentDescription = "Drugs Logo"
                )

                Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)

                if (!viewModel.getSelectedDrugList().contains(drug)) {
                    OutlinedButton(onClick = { viewModel.selectDrug(drug) },
                        modifier = Modifier.height(40.dp)) {
                        Text(text = "Add",fontSize = 17.sp)
                    }
                }
                Spacer(modifier = Modifier.width(0.5.dp))
            }
        }
    }
}
