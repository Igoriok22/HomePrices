package com.example.testapp.system

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ISchedulers {
    fun ui(): Scheduler
    fun io(): Scheduler
}

class AppSchedulers : ISchedulers
{
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()
}