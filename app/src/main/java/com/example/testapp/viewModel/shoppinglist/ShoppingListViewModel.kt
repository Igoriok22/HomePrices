package com.example.testapp.viewModel.shoppinglist

import android.arch.lifecycle.MutableLiveData
import com.example.testapp.domain.interfaces.IDashboardInteractor
import com.example.testapp.domain.model.ShoppingList
import com.example.testapp.system.ISchedulers
import com.example.testapp.utils.MutableDisposable
import com.example.testapp.viewModel.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

class ShoppingListViewModel (
        router: Router,
        schedulers: ISchedulers,
        var dashboardInteractor: IDashboardInteractor): BaseViewModel(router,schedulers){

    private val md = MutableDisposable()
    var shoppingList = MutableLiveData<ShoppingList>()

    fun getShoppingList(key: String) {
        md.disposable = dashboardInteractor.getShoppingList(key)
                .subscribeBy(
                        onSuccess = ::setListToView,
                        onError = ::handleError
                )
    }

    private fun setListToView(products: ShoppingList){
        shoppingList.value = products
    }
}