package com.example.testapp.domain.interfaces

import com.example.testapp.domain.model.CurrentExchangeRateCacheModel
import com.example.testapp.domain.model.Product
import com.example.testapp.domain.model.ShoppingList
import io.reactivex.Completable
import io.reactivex.Single

interface IDashboardInteractor {
    fun getExchangeRate(query: String): Single<CurrentExchangeRateCacheModel>
    fun getStorageExchangeRate(): Single<CurrentExchangeRateCacheModel>
    fun getArchiveList(): Single<List<String>>
    fun getShoppingList(date: String): Single<ShoppingList>
    fun saveInShoppingList(date: String, product: Product): Single<ShoppingList>
}