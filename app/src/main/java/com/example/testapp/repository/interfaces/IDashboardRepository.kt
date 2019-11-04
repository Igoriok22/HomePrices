package com.example.testapp.repository.interfaces

import com.example.testapp.domain.model.CurrentExchangeRate
import com.example.testapp.domain.model.Product
import com.example.testapp.domain.model.ShoppingList
import io.reactivex.Completable
import io.reactivex.Single

interface IDashboardRepository {
    fun getExchangeRate(query: String): Single<CurrentExchangeRate>

    fun getStorageExchangeRate(): Single<CurrentExchangeRate>

    fun getArchiveList(): Single<List<String>>

    fun getShoppingList(date: String): Single<ShoppingList>

    fun saveInShopInShoppingList(date: String, product: Product): Single<ShoppingList>
}