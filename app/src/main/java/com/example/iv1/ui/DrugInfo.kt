package com.example.iv1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Text(text = "Drug name : ",color = Color.Blue, fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.drug_name,
            style = TextStyle(fontSize = 25.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "pH : ",color = Color.Blue, fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.pH,
            style = TextStyle(fontSize = 22.sp))

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Storage : ",color = Color.Blue, fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.storage,
            lineHeight = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 20.sp))

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "IV Fluids : ",color = Color.Blue, fontSize = MaterialTheme.typography.h5.fontSize)
        Text(text = drug.iv_fluid,
            lineHeight = 30.sp,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontSize = 20.sp))
    }
}
