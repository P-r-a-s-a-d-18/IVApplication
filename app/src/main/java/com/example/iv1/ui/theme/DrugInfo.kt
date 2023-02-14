package com.example.iv1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun DisplayDrug(
    viewModel: DrugViewModel
) {
    val drug = viewModel.getDrug()
    InfoBox(drug)
}

@Composable
fun InfoBox(drug: Drug) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Drug : ", fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h6.fontSize)

        Text(text = "pH : ", fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.pH, fontSize = MaterialTheme.typography.h6.fontSize)

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Storage : ", fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.storage, fontSize = MaterialTheme.typography.h6.fontSize)

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "IV Fluids : ", fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.iv_fluid, fontSize = MaterialTheme.typography.h6.fontSize)
    }
}
