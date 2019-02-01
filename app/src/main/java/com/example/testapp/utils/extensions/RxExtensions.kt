package com.example.testapp.utils.extensions

import com.example.testapp.system.ISchedulers
import com.example.testapp.system.NoConnectivityException
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.setupLoader(loadingHandler: (Boolean) -> Unit): Observable<T> {
    return this.doOnSubscribe { loadingHandler(true) }
            .doOnNext { loadingHandler(false) }
            .doFinally { loadingHandler(false) }
}

fun <T> Single<T>.setupLoader(loadingHandler: (Boolean) -> Unit): Single<T> {
    return this.doOnSubscribe { loadingHandler(true) }
            .doFinally { loadingHandler(false) }
}

fun Completable.setupLoader(loadingHandler: (Boolean) -> Unit): Completable {
    return this.doOnSubscribe { loadingHandler(true) }
            .doFinally { loadingHandler(false) }

}

fun <T> Single<T>.doOnIoResultOnUi(schedulers: ISchedulers): Single<T> = observeOn(schedulers.ui())
        .subscribeOn(schedulers.io())


fun Completable.doOnIoResultOnUi(schedulers: ISchedulers): Completable = observeOn(schedulers.ui())
        .subscribeOn(schedulers.io())

fun defaultInterval(): Observable<Unit> =
        Observable.concat(Observable.just(0), Observable.interval(15, TimeUnit.SECONDS)).map { Unit }

fun <T> Observable<T>.doOnIoResultOnUi(schedulers: ISchedulers): Observable<T> = observeOn(schedulers.ui())
        .subscribeOn(schedulers.io())

fun <T> Single<T>.handleNetwork(): Single<T> = onErrorResumeNext { e ->
    if (e is UnknownHostException)
        Single.error(NoConnectivityException())
    else Single.error(e)
}