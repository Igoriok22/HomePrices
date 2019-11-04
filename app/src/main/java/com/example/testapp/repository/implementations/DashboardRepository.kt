package com.example.testapp.repository.implementations

import com.example.testapp.domain.model.AllShoppingList
import com.example.testapp.domain.model.CurrentExchangeRate
import com.example.testapp.domain.model.Product
import com.example.testapp.domain.model.ShoppingList
import com.example.testapp.network.OmniViewApi
import com.example.testapp.repository.interfaces.IDashboardRepository
import com.example.testapp.utils.HawkStorage
import com.example.testapp.utils.extensions.handleNetwork
import io.reactivex.Completable
import io.reactivex.Single

class DashboardRepository(
        private val api: OmniViewApi,
        private val storage: HawkStorage
) : IDashboardRepository {

    override fun getStorageExchangeRate(): Single<CurrentExchangeRate> = Single.fromCallable { storage.get<CurrentExchangeRate>() }

    override fun getExchangeRate(query: String): Single<CurrentExchangeRate> = api.getExchangeRate(query)
            .handleNetwork()
            .doOnSuccess {
                it.currentExchangeRateSymbols = query
                storage.put(it)
            }

    override fun getArchiveList(): Single<List<String>> {
        return if (storage.exists<AllShoppingList>()) {
            Single.fromCallable { storage.get<AllShoppingList>(HawkStorage.DATES).dates?.toList() }
        } else {
            Single.fromCallable { ArrayList<String>() }
        }
    }

    override fun getShoppingList(date: String): Single<ShoppingList> {
        return if (storage.exists<ShoppingList>(date)) {
            Single.fromCallable { storage.get<ShoppingList>(date) }
        } else {
            Single.fromCallable { ShoppingList("", ArrayList()) }
        }
    }

    override fun saveInShopInShoppingList(date: String, product: Product): Single<ShoppingList> {
        if (storage.exists<ShoppingList>(date)) {
            val shoppingList = storage.get<ShoppingList>(date)
            shoppingList.products.add(product)
            storage.put(shoppingList, date)
            if(!storage.exists<AllShoppingList>(HawkStorage.DATES)){
                val shoppingDates = storage.get<AllShoppingList>(HawkStorage.DATES)
                shoppingDates.dates?.add(date)
            }
        } else {
            storage.put(ShoppingList(date, mutableListOf(product)), date)
        }
        return Single.fromCallable { storage.get<ShoppingList>(date) }
    }
}


