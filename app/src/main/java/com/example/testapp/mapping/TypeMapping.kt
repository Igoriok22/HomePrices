package com.example.testapp.mapping

import com.example.testapp.R
import com.example.testapp.repository.model.Profile

fun Profile.toDomainModel(): com.example.testapp.domain.model.Profile =
        com.example.testapp.domain.model.Profile("$firstName $lastName", email)

val String.currencyLogoRes : Int
    get() = when(this){
        "EUR" -> R.drawable.eur_flag
        "USD" -> R.drawable.usa_flag
        "PLN" -> R.drawable.pln_flag
        "UAH" -> R.drawable.uah_flag
        else -> throw IllegalArgumentException("Invalid currency symbol")
    }