package com.example.iv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun ShowSelectedList(
    drugs: ArrayList<Drug>,
    onCheckBtnClicked: (ArrayList<Drug>) -> Unit,
    onCancelBtnClicked: () -> Unit = {},
    viewModel: DrugViewModel
) {
    Column(modifier = Modifier.padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize(0.93f)) {
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
        }
    }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue
                        ),
                        onClick = { onCheckBtnClicked(drugs) }, modifier = Modifier
                            .width(210.dp)
                            .height(50.dp)
                    ) {
                        Box(
                            modifier = Modifier.padding(horizontal = 7.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Check Incompatibility",
                                color = Color.White,
                                style = TextStyle(fontSize = 17.sp)
                            )
                        }
                    }

              //  Spacer(modifier = Modifier.width(2.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue
                        ),
                        onClick = { onCancelBtnClicked() }, modifier = Modifier
                            .width(110.dp)
                            .height(50.dp)
                    ) {
                        Box(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Cancel",
                                color = Color.White,
                                style = TextStyle(fontSize = 18.sp)
                            )
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
            .height(90.dp)
            .padding(10.dp)
    ) {
        Card(elevation = 10.dp,modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(0.1.dp))

                Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)

                Spacer(modifier = Modifier.width(20.dp))

                OutlinedButton(onClick = { viewModel.removeDrug(drug) }) {
                    Text(text = "Remove", fontSize = 17.sp)
                }
                Spacer(modifier = Modifier.width(0.2.dp))
            }
        }
    }
}
