package com.example.testapp.utils

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

/**
 * Disposable container, that disposes previous
 * disposable after new one is set
 */
class MutableDisposable : Disposable {
    var disposable: Disposable = Disposables.empty()
        set(value) {
            field.dispose()
            field = value
        }

    override fun isDisposed(): Boolean {
        return disposable.isDisposed
    }

    override fun dispose() {
        disposable.dispose()
    }
}