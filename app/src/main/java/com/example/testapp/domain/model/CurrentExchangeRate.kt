package com.example.testapp.domain.model

import com.google.gson.annotations.SerializedName

class CurrentExchangeRate(
    val EUR_UAH: CurrencyVal? = null,
    val USD_UAH: CurrencyVal? = null,
    val PLN_UAH: CurrencyVal? = null,
    val UAH_UAH: CurrencyVal? = null,
    val EUR_PLN: CurrencyVal? = null,
    val USD_PLN: CurrencyVal? = null,
    val PLN_PLN: CurrencyVal? = null,
    val UAH_PLN: CurrencyVal? = null,
    val EUR_USD: CurrencyVal? = null,
    val USD_USD: CurrencyVal? = null,
    val PLN_USD: CurrencyVal? = null,
    val UAH_USD: CurrencyVal? = null,
    val EUR_EUR: CurrencyVal? = null,
    val USD_EUR: CurrencyVal? = null,
    val PLN_EUR: CurrencyVal? = null,
    val UAH_EUR: CurrencyVal? = null
){
    fun getCurreencyRate(): Double{
        var listOfCurrencys = listOf(EUR_UAH, USD_UAH, PLN_UAH, UAH_UAH,
                                                     EUR_PLN, USD_PLN, PLN_PLN, UAH_PLN,
                                                     EUR_USD, USD_USD, PLN_USD, UAH_USD,
                                                     EUR_EUR, USD_EUR, PLN_EUR, UAH_EUR)
        for(i in listOfCurrencys){
           if(i != null) return i.value
        }
        return 0.0
    }
}


data class CurrencyVal(
        @SerializedName("val")
        var value: Double
)