package com.example.iv1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iv1.data.Drug
import com.example.iv1.data.DrugViewModel
import com.example.iv1.ui.composables.*

enum class IVScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    DrugList(title = R.string.start),
    DrugInfo(title = R.string.drug_info),
    SelectedDrugs(title = R.string.review),
    Results(title = R.string.incomp_res),
    ResultDetails(title = R.string.incomp_details),
    IRCalc(title = R.string.ir_calc),
    IncompList(title = R.string.incomp_list)
}

@Composable
fun IVAppBar(
    currentScreen: IVScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title ={ Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
//        navigationIcon ={
////            if(canNavigateBack) {
////                IconButton(onClick = navigateUp) {
////                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
////                }
////            }
//        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = Color.Black,
        elevation = 10.dp
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.DrugList,
        NavigationItem.SelectedDrugs,
        NavigationItem.Results,
        NavigationItem.IRCalc
    )
    BottomNavigation(
        backgroundColor = Color.Blue,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?:IVScreen.Start.name
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Start(
    modifier: Modifier = Modifier,
    viewModel: DrugViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = IVScreen.valueOf(
        backStackEntry?.destination?.route ?:IVScreen.Start.name
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            IVAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = IVScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = IVScreen.Start.name) {
                StartScreen(
//                    onIRCalcButtonClicked = { navController.navigate(IVScreen.IRCalc.name) },
//                    onCompatibilityCheckButtonClicked = {
//                        navController.navigate(IVScreen.DrugList.name)
//                    }
                )
            }

            composable(route = IVScreen.DrugList.name) {
                SetData(
                    viewModel = viewModel,
//                    onDoneBtnClicked = {
//                        navController.navigate(IVScreen.SelectedDrugs.name)
//                    },
                    onListItemClicked = {
                        navController.navigate(IVScreen.DrugInfo.name)
                    }
                )
            }

            composable(route = IVScreen.SelectedDrugs.name) {
                ShowSelectedList(
                    drugs = viewModel.getSelectedDrugList(),
//                    onCheckBtnClicked = {
//                        navController.navigate(IVScreen.Results.name)
//                    },
//                    onCancelBtnClicked = {
//                        cancelAndNavigateToStart(navController, viewModel)
//                    },
                    viewModel
                )
            }

            composable(route = IVScreen.Results.name) {
                GetCheck(
                    viewModel = viewModel,
                    onElementClicked = {
                        navController.navigate(IVScreen.ResultDetails.name)
                    },
//                    onBackBtnClicked = {
//                        cancelAndNavigateToStart(navController, viewModel)
//                    }
                )
            }

            composable(route = IVScreen.ResultDetails.name) {
                ResDetail(
                    viewModel = viewModel
                )
            }

            composable(route = IVScreen.IRCalc.name) {
                IRScreen()
            }

            composable(route = IVScreen.DrugInfo.name) {
                DisplayDrug(
                    viewModel = viewModel,
                    onItemClicked = { navController.navigate(IVScreen.IncompList.name) }
                )
            }

            composable(route = IVScreen.IncompList.name) {
                DisplayIncompList(drug = Drug())
            }
        }
    }
}

private fun cancelAndNavigateToStart(
    navController: NavHostController,
    viewModel: DrugViewModel
) {
    viewModel.tempList.clear()
    navController.popBackStack(IVScreen.DrugList.name, inclusive = false)
}