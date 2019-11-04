package com.example.testapp.domain.implementations

import com.example.testapp.domain.interfaces.IDashboardInteractor
import com.example.testapp.domain.model.CurrentExchangeRateCacheModel
import com.example.testapp.domain.model.Product
import com.example.testapp.domain.model.ShoppingList
import com.example.testapp.repository.interfaces.IDashboardRepository
import io.reactivex.Completable
import io.reactivex.Single

class DashboardInteractor(
        private val dashboardRepository: IDashboardRepository
) : IDashboardInteractor {

   override fun getStorageExchangeRate(): Single<CurrentExchangeRateCacheModel> = dashboardRepository.getStorageExchangeRate()
           .map { it.getCurreencyRate() }


   override fun getExchangeRate(query: String): Single<CurrentExchangeRateCacheModel> = dashboardRepository.getExchangeRate(query)
           .map { it.getCurreencyRate() }

   override fun getArchiveList(): Single<List<String>> = dashboardRepository.getArchiveList()

   override fun getShoppingList(date: String) = dashboardRepository.getShoppingList(date)

   override fun saveInShoppingList(date: String, product: Product) = dashboardRepository.saveInShopInShoppingList(date, product)

}