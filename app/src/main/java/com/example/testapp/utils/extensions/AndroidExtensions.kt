package com.example.testapp.utils.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.support.v4.app.Fragment
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.example.testapp.view.BaseActivity

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, android.arch.lifecycle.Observer {
        it?.let(observer)
    })
}

val Fragment.parentActivity: BaseActivity?
    get() = activity as? BaseActivity

fun LottieAnimationView.initLoader(isLoading: Boolean) {
    if (isLoading) {
        playAnimation()
        visibility = View.VISIBLE
    } else {
        pauseAnimation()
        animation?.reset()
        visibility = View.GONE
    }
}