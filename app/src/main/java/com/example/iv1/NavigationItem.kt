package com.example.iv1

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem(IVScreen.Start.name, R.drawable.ic_home, "Home")
    object DrugList : NavigationItem(IVScreen.DrugList.name, R.drawable.bullet_list, "Drugs")
    object SelectedDrugs : NavigationItem(IVScreen.SelectedDrugs.name, R.drawable.checklist, "Selected")
    object Results : NavigationItem(IVScreen.Results.name, R.drawable.assignment_turned_in, "Results")
    object IRCalc : NavigationItem(IVScreen.IRCalc.name, R.drawable.vaccines, "IR")
}
