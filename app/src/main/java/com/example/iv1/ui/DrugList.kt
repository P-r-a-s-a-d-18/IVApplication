package com.example.iv1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iv1.R
import com.example.iv1.data.DataState
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel
import java.util.*

@Composable
fun SetData(
    viewModel: DrugViewModel,
    onDoneBtnClicked: (MutableList<Drug>) -> Unit,
    onListItemClicked: () -> Unit
) {
    when(val result = viewModel.response.value) {
        is DataState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            val textState = remember { mutableStateOf(TextFieldValue("")) }
            Column{
                SearchView(textState)
                ShowDrugList(
                    result.data,
                    state = textState,
                    onDoneBtnClicked = onDoneBtnClicked,
                    onListItemClicked = onListItemClicked,
                    viewModel = viewModel
                )
            }
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
    drugs:MutableList<Drug>,
    onDoneBtnClicked: (MutableList<Drug>) -> Unit,
    onListItemClicked: () -> Unit,
    viewModel: DrugViewModel,
    state: MutableState<TextFieldValue>
) {
    var filteredDrugs: MutableList<Drug>

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .fillMaxSize(0.93f)
    ) {

        LazyColumn {
            val searchedText = state.value.text
            filteredDrugs = if (searchedText.isEmpty()) {
                drugs
            } else {
                val resultList = ArrayList<Drug>()
                for (drug in drugs) {
                    if (drug.drug_name.lowercase(Locale.getDefault())
                            .contains(searchedText.lowercase(Locale.getDefault()))
                    ) {
                        resultList.add(drug)
                    }
                }
                resultList
            }
            items(filteredDrugs) { drug ->
                ListItem(drug, onListItemClicked, viewModel)
            }
        }
    }
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue
            ),
            onClick = { onDoneBtnClicked(drugs) }, modifier = Modifier
                .width(110.dp)
                .height(50.dp)
        ) {
                Text(text = "Next", color = Color.White, style = TextStyle(fontSize = 18.sp))
        }
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
           .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        placeholder = {
            Text(
                text = "Search drug",
                style = TextStyle(fontSize = 16.sp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = colorResource(id = R.color.white),
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black
        )
    )
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
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment =Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.width(5.dp))

                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.drug2),
                    contentDescription = "Drugs Logo"
                )
                Spacer(modifier = Modifier.width(15.dp))

                Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)
                
                Spacer(modifier = Modifier.weight(1f))

                if (!viewModel.getSelectedDrugList().contains(drug)) {
                    IconButton(onClick = { viewModel.selectDrug(drug) }) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Select ${drug.drug_name}")
                    }
                }
                    else{
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Selected",
                        tint=Color.Green,
                        modifier = Modifier.size(30.dp))

                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
