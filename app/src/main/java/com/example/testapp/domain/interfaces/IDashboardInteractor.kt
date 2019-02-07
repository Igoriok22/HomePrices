package com.example.testapp.domain.interfaces

import com.example.testapp.domain.model.CurrentExchangeRateCacheModel
import com.example.testapp.domain.model.ShoppingList
import io.reactivex.Single

interface IDashboardInteractor {
    fun getExchangeRate(query: String): Single<CurrentExchangeRateCacheModel>
    fun getStorageExchangeRate(): Single<CurrentExchangeRateCacheModel>
    fun getArchiveList(): Single<List<String>>
    fun getShoppingList(key: String): Single<ShoppingList>
}