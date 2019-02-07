package com.example.testapp.viewModel.dashboard

import android.arch.lifecycle.MutableLiveData
import com.example.testapp.domain.interfaces.IDashboardInteractor
import com.example.testapp.domain.model.CurrentExchangeRateCacheModel
import com.example.testapp.system.ISchedulers
import com.example.testapp.utils.MutableDisposable
import com.example.testapp.viewModel.BaseViewModel
import ru.terrakok.cicerone.Router

class DashboardViewModel(
        router: Router,
        schedulers: ISchedulers,
        var dashboardInteractor: IDashboardInteractor): BaseViewModel(router,schedulers){

    private val md = MutableDisposable()
    var currencyRate = MutableLiveData<Double>()

    var foreignSpinerPosition = MutableLiveData<Int>()
    var nativeSpinnerPosition = MutableLiveData<Int>()

    fun getCurrencyRate(query: String) {
        md.disposable = dashboardInteractor.getExchangeRate(query)
                .manageSchedulers()
                .defaultLoader()
                .subscribe(::setCurrencyRateToView, ::handleError)
    }

    fun getCurrencyRateFromCache() {
        md.disposable = dashboardInteractor.getStorageExchangeRate()
                .subscribe(::setCurrencyRateToView, ::handleError)
    }

    private fun setCurrencyRateToView(result: CurrentExchangeRateCacheModel){
        currencyRate.value = result.currentRate
        foreignSpinerPosition.value = result.foreignSpinerPosition
        nativeSpinnerPosition.value=result.nativeSpinnerPosition
    }
}