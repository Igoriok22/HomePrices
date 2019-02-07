package com.example.testapp.viewModel.purchasesarchive

import android.arch.lifecycle.MutableLiveData
import com.example.testapp.Screens
import com.example.testapp.domain.interfaces.IDashboardInteractor
import com.example.testapp.system.ISchedulers
import com.example.testapp.utils.MutableDisposable
import com.example.testapp.viewModel.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

class PurchasesArchiveViewModel (
        router: Router,
        schedulers: ISchedulers,
        var dashboardInteractor: IDashboardInteractor): BaseViewModel(router,schedulers){

    private val md = MutableDisposable()
    var archiveList = MutableLiveData<List<String>>()

    fun getArchiveList() {
        md.disposable = dashboardInteractor.getArchiveList()
                .subscribeBy(
                        onSuccess = ::setArchiveListToView,
                        onError = ::handleError
                )
    }

    fun goToShopingList(date: String) = router.navigateTo(Screens.ShoppingList(date))



    private fun setArchiveListToView(dates: List<String>){
        archiveList.value = dates
    }
}