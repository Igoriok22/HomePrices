package com.example.testapp.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.testapp.system.ISchedulers
import com.example.testapp.utils.SingleLiveEvent
import com.example.testapp.utils.extensions.doOnIoResultOnUi
import com.example.testapp.utils.extensions.setupLoader
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.terrakok.cicerone.Router

abstract class BaseViewModel(
        protected val router: Router,
        protected val schedulers: ISchedulers
): ViewModel(){

    open val toastMessage = SingleLiveEvent<String>()
    open val toastRes = SingleLiveEvent<Int>()

    val isLoading = MutableLiveData<Boolean>().apply { value = false }


    open fun handleError(e: Throwable) {

    }

    fun <T> Single<T>.defaultLoader() = setupLoader { isLoading.value = it }
    fun <T> Observable<T>.defaultLoader() = setupLoader { isLoading.value = it }
    fun Completable.defaultLoader() = setupLoader { isLoading.value = it }

    fun <T> Single<T>.manageSchedulers() = doOnIoResultOnUi(schedulers)
    fun <T> Observable<T>.manageSchedulers() = doOnIoResultOnUi(schedulers)
    fun Completable.manageSchedulers() = doOnIoResultOnUi(schedulers)
}