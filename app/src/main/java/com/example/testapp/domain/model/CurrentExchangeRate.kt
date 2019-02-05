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

    var currentExchangeRateSymbols: String?= null

    fun getCurreencyRate(): CurrentExchangeRateCacheModel?{

        val firstSymbol = currentExchangeRateSymbols!!.substring(0,3)
        val secondSymbol = currentExchangeRateSymbols!!.substring(4)

        var listOfCurrencys = listOf(EUR_UAH, USD_UAH, PLN_UAH, UAH_UAH,
                                                     EUR_PLN, USD_PLN, PLN_PLN, UAH_PLN,
                                                     EUR_USD, USD_USD, PLN_USD, UAH_USD,
                                                     EUR_EUR, USD_EUR, PLN_EUR, UAH_EUR)
        for(i in listOfCurrencys){
           if(i != null) return CurrentExchangeRateCacheModel(i.value, getSpinnerPositionFromRateSymbols(firstSymbol),getSpinnerPositionFromRateSymbols(secondSymbol))
        }
        return null
    }

    private fun getSpinnerPositionFromRateSymbols(symbol: String): Int{
        return when(symbol){
            "EUR" -> 0
            "USD" -> 1
            "PLN" -> 2
            "UAH" -> 3
            else -> 0
        }
    }
}


data class CurrencyVal(
        @SerializedName("val")
        var value: Double
)

data class CurrentExchangeRateCacheModel(
        var currentRate: Double,
        var foreignSpinerPosition: Int,
        var nativeSpinnerPosition: Int
)