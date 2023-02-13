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
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartCheck(viewModel: DrugViewModel) {
    val drugList = viewModel.getSelectedDrugList()
    val toCheck: ArrayList<Pair<Drug, Drug>> = ArrayList()
    for(i in 0 until drugList.size - 1) {
        for(j in i+1 until drugList.size) {
            if(i != j) {
                toCheck.add(Pair(drugList[i], drugList[j]))
            }
        }
    }
    LazyColumn {
        toCheck.forEach { pair ->
            stickyHeader {
                Text(
                    text = pair.first.drug_name.trim() + " -- " + pair.second.drug_name.trim(),
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }
            items(listOf(pair)) {drugPair ->
                DisplayCheck(drugPair.first, drugPair.second)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayCheck(drug1: Drug, drug2: Drug) {

    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize()
        .padding(10.dp),
    Arrangement.spacedBy(8.dp)) {
        Text(text = "Mixture", fontSize = MaterialTheme.typography.h4.fontSize)
        Text(text = drug1.type_of_incompatibility[drug2.drug_name]?.get(0)?.get("Mixture").toString(), fontSize = MaterialTheme.typography.h5.fontSize)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Type", fontSize = MaterialTheme.typography.h4.fontSize)
        Text(text = drug1.type_of_incompatibility[drug2.drug_name]?.get(0)?.get("Type").toString(), fontSize = MaterialTheme.typography.h5.fontSize)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Effect", fontSize = MaterialTheme.typography.h4.fontSize)
        Text(text = drug1.type_of_incompatibility[drug2.drug_name]?.get(0)?.get("Effect").toString(), fontSize = MaterialTheme.typography.h5.fontSize)
    }
}
