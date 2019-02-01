package com.example.testapp.domain.interfaces

import io.reactivex.Single

interface IDashboardInteractor {
    fun getExchangeRate(query: String): Single<Double>
    fun getStorageExchangeRate(): Single<Double>
}