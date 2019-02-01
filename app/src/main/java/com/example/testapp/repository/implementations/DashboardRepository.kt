package com.example.testapp.repository.implementations

import com.example.testapp.domain.model.CurrentExchangeRate
import com.example.testapp.network.OmniViewApi
import com.example.testapp.repository.interfaces.IDashboardRepository
import com.example.testapp.utils.HawkStorage
import com.example.testapp.utils.extensions.handleNetwork
import io.reactivex.Single

class DashboardRepository(
        private val api: OmniViewApi,
        private val storage: HawkStorage
) : IDashboardRepository {
    override fun getStorageExchangeRate(): Single<CurrentExchangeRate> = Single.fromCallable { storage.get<CurrentExchangeRate>() }


    override fun getExchangeRate(query: String): Single<CurrentExchangeRate> = api.getExchangeRate(query)
            .handleNetwork()
            .doOnSuccess { storage.put(it) }
}