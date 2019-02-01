package com.example.testapp.mapping

import com.example.testapp.R

val String.currencyLogoRes : Int
    get() = when(this){
        "EUR" -> R.drawable.eur_flag
        "USD" -> R.drawable.usa_flag
        "PLN" -> R.drawable.pln_flag
        "UAH" -> R.drawable.uah_flag
        else -> throw IllegalArgumentException("Invalid currency symbol")
    }