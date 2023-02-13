package com.example.iv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.data.Drug

@Composable
fun ShowSelectedList(
    drugs: ArrayList<Drug>,
    onCheckBtnClicked: (ArrayList<Drug>) -> Unit,
    onCancelBtnClicked: () -> Unit = {},
    viewModel: DrugViewModel
) {
    Column(modifier = Modifier.padding(10.dp)) {
        if (drugs.isEmpty() || drugs.size == 1) {
            Text(
                text = "Not enough drugs to perform compatibility check.",
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn {
                items(drugs) { drug ->
                    SelectedListItem(drug, viewModel)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedButton(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue
                        ),
                        onClick = { onCheckBtnClicked(drugs) }, modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Check Incompatibility",
                            color = Color.White,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(1.dp))

                Box(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedButton(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue
                        ),
                        onClick = { onCancelBtnClicked() }, modifier = Modifier
                            .width(110.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            color = Color.White,
                            style = TextStyle(fontSize = 17.sp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SelectedListItem(
    drug: Drug,
    viewModel: DrugViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment =Alignment.CenterVertically
        ) {
            Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)
            OutlinedButton(onClick = { viewModel.removeDrug(drug) }) {
                Text(text = "Remove",fontSize = 17.sp)
            }
        }

    }
}
