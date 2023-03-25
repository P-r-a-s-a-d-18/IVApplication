package com.example.iv1.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun ShowSelectedList(
    drugs: MutableList<Drug>,
//    onCheckBtnClicked: (MutableList<Drug>) -> Unit,
//    onCancelBtnClicked: () -> Unit = {},
    viewModel: DrugViewModel
) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize()) {
        if (drugs.isEmpty()) {
            Text(
                modifier = Modifier.padding(10.dp),
                fontFamily = FontFamily.Serif,
                lineHeight = 30.sp,
                text = "Select Drugs to check compatibility.",
                style = TextStyle(fontSize = 20.sp)
            )
        } else {
            LazyColumn(
                state = rememberLazyListState()
            ) {
                items(drugs) { drug ->
                    SelectedListItem(drug, viewModel)
                }
            }
        }
    }
            Spacer(modifier = Modifier.height(10.dp))

//            Row(
//                modifier = Modifier.fillMaxSize().padding(6.dp),
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.Bottom
//            ) {
//                    Button(
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color.Blue
//                        ),
//                        onClick = { onCheckBtnClicked(drugs) }, modifier = Modifier
//                            .width(220.dp)
//                            .height(40.dp)
//                    ) {
//                        Text(
//                            text = "Check Incompatibility",
//                            color = Color.White,
//                            style = TextStyle(fontSize = 18.sp)
//                        )
//                    }
//
//              //  Spacer(modifier = Modifier.width(2.dp))
//
//                    Button(
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = Color.Blue
//                        ),
//                        onClick = { onCancelBtnClicked() }, modifier = Modifier
//                            .width(110.dp)
//                            .height(40.dp)
//                    ) {
//                        Text(
//                            text = "Cancel",
//                            color = Color.White,
//                            style = TextStyle(fontSize = 18.sp)
//                        )
//                    }
//            }
        }
@Composable
fun SelectedListItem(
    drug: Drug,
    viewModel: DrugViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(8.dp)
    ) {
        Card(elevation = 6.dp,modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(20.dp))

                Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h6.fontSize)

               // Spacer(modifier = Modifier.width(20.dp))
                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { viewModel.removeDrug(drug) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove ${drug.drug_name}")
                }
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
    }
}
