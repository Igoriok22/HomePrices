package com.example.testapp.repository.interfaces

import com.example.testapp.domain.model.CurrentExchangeRate
import com.example.testapp.domain.model.ShoppingList
import io.reactivex.Single

interface IDashboardRepository {
    fun getExchangeRate(query: String): Single<CurrentExchangeRate>

    fun getStorageExchangeRate(): Single<CurrentExchangeRate>

    fun getArchiveList(): Single<List<String>>

    fun getShoppingList(kay: String): Single<ShoppingList>
}