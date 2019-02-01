package com.example.testapp.domain.implementations

import com.example.testapp.domain.interfaces.IDashboardInteractor
import com.example.testapp.repository.interfaces.IDashboardRepository
import io.reactivex.Single

class DashboardInteractor(
        private val dashboardRepository: IDashboardRepository
) : IDashboardInteractor {
   override fun getStorageExchangeRate(): Single<Double> = dashboardRepository.getStorageExchangeRate()
           .map { it.getCurreencyRate() }


   override fun getExchangeRate(query: String): Single<Double> = dashboardRepository.getExchangeRate(query)
           .map { it.getCurreencyRate() }
}