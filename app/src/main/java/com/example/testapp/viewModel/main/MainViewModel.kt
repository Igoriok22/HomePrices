package com.example.testapp.viewModel.main

import com.example.testapp.Screens
import com.example.testapp.system.ISchedulers
import com.example.testapp.viewModel.BaseViewModel
import ru.terrakok.cicerone.Router

class MainViewModel(
        router: Router,
        schedulers: ISchedulers
) : BaseViewModel(router, schedulers) {


    fun goToCamera() { router.newRootScreen(Screens.Camera()) }
    fun goToDashboard() { router.newRootScreen(Screens.Dashboard()) }

}