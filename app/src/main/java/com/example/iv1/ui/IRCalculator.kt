package com.example.iv1.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.iv1.R
import java.text.NumberFormat

@Composable
fun IRScreen() {
    var volInput by remember {
        mutableStateOf("")
    }
    val volume = volInput.toDoubleOrNull() ?: 0.0

    var timeInput by remember {
        mutableStateOf("")
    }
    val time = timeInput.toDoubleOrNull() ?: 0.0

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(32.dp),
        Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.ir_calc),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditNumberField(label = R.string.ir_vol,
            value = volInput,
            onValueChange = { volInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        EditNumberField(label = R.string.ir_time,
            value = timeInput,
            onValueChange = { timeInput = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

//        EditNumberField(label = R.string.ir_drop_factor,
//            value = factorInput,
//            onValueChange = { factorInput = it },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = { focusManager.clearFocus(true) }
//            )
//        )
        val dropFactor = dropFactorMenu()

        Spacer(modifier = Modifier.height(24.dp))

        val ir = calculateIR(volume, time, dropFactor)

        Text(
            text = stringResource(id = R.string.ir_result, ir),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

fun calculateIR(volume: Double, time: Double, dropFactor: Int): String {
    var ir = (volume / time) * dropFactor
    if (ir.isNaN()) {
        ir = 0.0
    }
    return NumberFormat.getNumberInstance().format(ir)
}

@Composable
fun EditNumberField(@StringRes label: Int,
                    value: String,
                    onValueChange: (String) -> Unit,
                    keyboardOptions: KeyboardOptions,
                    keyboardActions: KeyboardActions
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        Modifier.background(color = Color.LightGray.copy(alpha = 0.1f)),
        label = { Text (
            text = stringResource(id = label),
            modifier = Modifier.width(200.dp)
        )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true
    )
}

@Composable
fun dropFactorMenu(): Int {
    var mExpanded by remember { mutableStateOf(false) }

    val mCities = listOf(20, 60)

    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text("Drop Factor")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label.toString()
                    mExpanded = false
                }) {
                    Text(text = label.toString())
                }
            }
        }
    }
    return mSelectedText.toIntOrNull()?: 0
}
