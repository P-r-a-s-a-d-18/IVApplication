package com.example.iv1.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iv1.IVScreen
import com.example.iv1.R
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel

@Composable
fun DisplayDrug(
    viewModel: DrugViewModel,
    navController: NavController
) {
        val drug = viewModel.getDrug()
        InfoBox(drug, navController)
}

@Composable
fun InfoBox(drug: Drug, navController: NavController) {
    LazyColumn() {
        items(1) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(150.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        painter = painterResource(id = R.drawable.storage),
                        contentDescription = "Drug storage image"
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .background(colorResource(id = R.color.gray))
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(PaddingValues(8.dp, 16.dp))
                ) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Drug: ", fontSize = 20.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = drug.drug_name, fontFamily = FontFamily.Serif, fontSize = 20.sp, color = Color.Black)
                }

//                Text(
//                    text = "Drug name : ",
//                    color = Color.Blue,
//                    fontSize = MaterialTheme.typography.h5.fontSize
//                )
//                Text(
//                    text = drug.drug_name,
//                    style = TextStyle(fontSize = 25.sp)
//                )

                Row(
                    modifier = Modifier
                        .background(colorResource(id = R.color.gray))
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(PaddingValues(8.dp, 16.dp))
                ) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "pH: ", fontSize = 20.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = drug.pH, fontFamily = FontFamily.Serif, fontSize = 20.sp, color = Color.Black)
                }

//                Text(
//                    text = "pH : ",
//                    color = Color.Blue,
//                    fontSize = MaterialTheme.typography.h5.fontSize
//                )
//                Text(
//                    text = drug.pH,
//                    style = TextStyle(fontSize = 22.sp)
//                )

                Row(
                    modifier = Modifier
                        .background(colorResource(id = R.color.gray))
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(PaddingValues(8.dp, 16.dp))
                ) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Storage: ", fontSize = 20.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = drug.storage, fontFamily = FontFamily.Serif, fontSize = 20.sp, color = Color.Black)
                }

//                Text(
//                    text = "Storage : ",
//                    color = Color.Blue,
//                    fontSize = MaterialTheme.typography.h5.fontSize
//                )
//                Text(
//                    text = drug.storage,
//                    lineHeight = 30.sp,
//                    modifier = Modifier.fillMaxWidth(),
//                    style = TextStyle(fontSize = 20.sp)
//                )

                Row(
                    modifier = Modifier
                        .background(colorResource(id = R.color.gray))
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(PaddingValues(8.dp, 16.dp))
                ) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Diluent: ", fontSize = 20.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = drug.iv_fluid, fontFamily = FontFamily.Serif, fontSize = 20.sp, color = Color.Black)
                }

//                Text(
//                    text = "Diluent : ",
//                    color = Color.Blue,
//                    fontSize = MaterialTheme.typography.h5.fontSize
//                )
//                Text(
//                    text = drug.iv_fluid,
//                    lineHeight = 30.sp,
//                    modifier = Modifier.fillMaxWidth(),
//                    style = TextStyle(fontSize = 20.sp)
//                )
                Spacer(modifier = Modifier.height(4.dp))
                HeaderView(questionText = "INCOMPATIBLE DRUGS", onClickItem = { navController.navigate(IVScreen.IncompList.name) })
                Spacer(modifier = Modifier.height(4.dp))
                HeaderView(questionText = "COMPATIBLE DRUGS", onClickItem = { navController.navigate(IVScreen.CompList.name) })
            }
        }
    }
}

@Composable
fun HeaderView(questionText: String, onClickItem: () -> Unit) {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.gray))
            .clickable(
                indication = null, // Removes the ripple effect on tap
                interactionSource = remember { MutableInteractionSource() }, // Removes the ripple effect on tap
                onClick = onClickItem
            )
            .padding(8.dp)
    ) {
        Text(
            text = questionText,
            fontSize = 20.sp,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

//@Composable
//fun ExpandableView(answerText: String, isExpanded: Boolean) {
//    // Opening Animation
//    val expandTransition = remember {
//        expandVertically(
//            expandFrom = Alignment.Top,
//            animationSpec = tween(300)
//        ) + fadeIn(
//            animationSpec = tween(300)
//        )
//    }
//
//    // Closing Animation
//    val collapseTransition = remember {
//        shrinkVertically(
//            shrinkTowards = Alignment.Top,
//            animationSpec = tween(300)
//        ) + fadeOut(
//            animationSpec = tween(300)
//        )
//    }
//
//    AnimatedVisibility(
//        visible = isExpanded,
//        enter = expandTransition,
//        exit = collapseTransition
//    ) {
//        Box(
//            modifier = Modifier
//                .background(Color.White)
//                .padding(15.dp)
//        ) {
//            Text(
//                text = answerText,
//                fontSize = 16.sp,
//                color = Color.Black,
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
//        }
//    }
//}
