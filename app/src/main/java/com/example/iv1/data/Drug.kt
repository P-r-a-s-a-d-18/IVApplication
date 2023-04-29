package com.example.iv1.data

data class Drug (
    var drug_name: String = "",
    var id: Int = 0,
    var incompatible_drugs: ArrayList<String> = ArrayList(),
    var iv_fluid: String = "",
    var pH: String = "",
    var storage: String = "",
    var type_of_incompatibility: HashMap<String, ArrayList<HashMap<String, String>>> = HashMap(),
    var compatible_drugs: ArrayList<String> = ArrayList()
)
