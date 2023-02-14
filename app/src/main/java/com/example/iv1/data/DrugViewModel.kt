package com.example.iv1.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DrugViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    val tempList: ArrayList<Drug> = ArrayList()

    var drugInfo: Drug = Drug()

    var drug1: Drug = Drug()
    var drug2: Drug = Drug()

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        response.value = DataState.Loading
        val ref = FirebaseDatabase.getInstance().getReference()
        val drugRef = ref.child("Drugs")

        drugRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val drugList: ArrayList<Drug> = ArrayList()
                for(ds: DataSnapshot in snapshot.children) run {
                    val drug: Drug? = ds.getValue(Drug::class.java)
                    if (drug != null) {
                        drugList.add(drug)
                    }
                }
                response.value = DataState.Success(drugList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataState.Failure(error.message)
            }

        })
    }

    fun setDrug(drug: Drug) {
        drugInfo = drug
    }

    fun getDrug(): Drug {
        return drugInfo
    }

    fun selectDrug(drug: Drug) {
        if(!tempList.contains(drug)) {
            tempList.add(drug)
        }
    }

    fun removeDrug(drug: Drug) {
        if(tempList.contains(drug)) {
            tempList.remove(drug)
        }
    }

    fun getSelectedDrugList(): ArrayList<Drug> {
        return tempList
    }

    fun getToCheck(): ArrayList<Pair<Drug, Drug>> {
        val toCheck: ArrayList<Pair<Drug, Drug>> = ArrayList()
        for (i in 0 until tempList.size - 1) {
            for (j in i + 1 until tempList.size) {
                if (i != j) {
                    toCheck.add(Pair(tempList[i], tempList[j]))
                }
            }
        }
        return toCheck
    }

    fun setPair(pair: Pair<Drug, Drug>) {
        drug1 = pair.first
        drug2 = pair.second
    }

    fun getResultObject(): ArrayList<HashMap<String, String>> {
        return drug1.type_of_incompatibility[drug2.drug_name]!!
    }
}