package com.example.testapp

import android.app.Application
import com.example.testapp.di.appModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initStorage()
        startKoin(this, listOf(appModule))
    }

    private fun initStorage() {
        Hawk.init(this).build()
    }


}