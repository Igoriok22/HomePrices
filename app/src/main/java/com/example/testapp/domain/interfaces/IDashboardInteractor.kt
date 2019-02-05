package com.example.testapp.domain.interfaces

import com.example.testapp.domain.model.CurrentExchangeRateCacheModel
import io.reactivex.Single

interface IDashboardInteractor {
    fun getExchangeRate(query: String): Single<CurrentExchangeRateCacheModel>
    fun getStorageExchangeRate(): Single<CurrentExchangeRateCacheModel>
}