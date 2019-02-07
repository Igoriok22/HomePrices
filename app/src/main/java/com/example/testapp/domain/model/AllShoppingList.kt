package com.example.testapp.domain.model

class AllShoppingList{

    companion object {
        val dates: Set<String>? = null
    }

    fun getDates(): List<String>{
        var newDatesList = ArrayList<String>()
        for(date in dates!!){
            newDatesList.add(date)
        }
        return newDatesList
    }
}






