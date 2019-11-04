package com.example.testapp.viewModel.shoppinglist

import android.arch.lifecycle.MutableLiveData
import com.example.testapp.domain.interfaces.IDashboardInteractor
import com.example.testapp.domain.model.Product
import com.example.testapp.domain.model.ShoppingList
import com.example.testapp.system.ISchedulers
import com.example.testapp.utils.MutableDisposable
import com.example.testapp.viewModel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

class ShoppingListViewModel(
        router: Router,
        schedulers: ISchedulers,
        var dashboardInteractor: IDashboardInteractor) : BaseViewModel(router, schedulers) {

    private val cd = CompositeDisposable()
    var shoppingList = MutableLiveData<ShoppingList>()

    fun getShoppingList(key: String) {
        dashboardInteractor.getShoppingList(key)
                .subscribeBy(
                        onSuccess = ::setListToView,
                        onError = ::handleError
                )
                .addTo(cd)
    }

    private fun setListToView(products: ShoppingList) {
        shoppingList.value = products
    }

    fun saveInShopingList(date: String, product: Product) {
        dashboardInteractor.saveInShoppingList(date, product)
                .manageSchedulers()
                .subscribe({
                    setListToView(it)
                },
                        {})
                .addTo(cd)
    }
}