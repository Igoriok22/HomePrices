package com.example.testapp.viewModel.dashboard

import com.example.testapp.domain.implementations.SetupInteractor
import com.example.testapp.system.ISchedulers
import com.example.testapp.viewModel.BaseViewModel
import ru.terrakok.cicerone.Router

class DashboardViewModel(
        router: Router,
        shedulers: ISchedulers,
        interactor: SetupInteractor): BaseViewModel(router,shedulers){

}