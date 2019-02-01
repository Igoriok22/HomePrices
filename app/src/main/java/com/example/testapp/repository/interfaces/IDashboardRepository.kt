package com.example.testapp.repository.interfaces

import com.example.testapp.domain.model.CurrentExchangeRate
import io.reactivex.Single

interface IDashboardRepository {
    fun getExchangeRate(query: String): Single<CurrentExchangeRate>

    fun getStorageExchangeRate(): Single<CurrentExchangeRate>
}