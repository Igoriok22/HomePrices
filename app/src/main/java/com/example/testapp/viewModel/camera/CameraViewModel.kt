package com.example.testapp.viewModel.camera

import android.arch.lifecycle.MutableLiveData
import com.example.testapp.domain.interfaces.IDashboardInteractor
import com.example.testapp.domain.model.CurrentExchangeRateCacheModel
import com.example.testapp.system.ISchedulers
import com.example.testapp.utils.MutableDisposable
import com.example.testapp.viewModel.BaseViewModel
import ru.terrakok.cicerone.Router

class CameraViewModel (
        router: Router,
        schedulers: ISchedulers,
        var interactor: IDashboardInteractor): BaseViewModel(router,schedulers){


    private val md = MutableDisposable()
    var currencyRateMultiplier = MutableLiveData<Double>()

    fun getCurrencyRate() {
        md.disposable = interactor.getStorageExchangeRate()
                .subscribe(::setCurrencyRateToView, ::handleError)
    }

    private fun setCurrencyRateToView(result: CurrentExchangeRateCacheModel){
        currencyRateMultiplier.value = result.currentRate
    }
}