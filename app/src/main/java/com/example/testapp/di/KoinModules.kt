package com.example.testapp.di

import com.example.testapp.domain.implementations.SetupInteractor
import com.example.testapp.domain.interfaces.ISetupInteractor
import com.example.testapp.network.ApiClient
import com.example.testapp.repository.implementations.AuthRepository
import com.example.testapp.repository.implementations.ProfileRepository
import com.example.testapp.repository.interfaces.IAuthRepository
import com.example.testapp.repository.interfaces.IProfileRepository
import com.example.testapp.system.AppSchedulers
import com.example.testapp.system.ISchedulers
import com.example.testapp.viewModel.camera.CameraViewModel
import com.example.testapp.viewModel.dashboard.DashboardViewModel
import com.example.testapp.viewModel.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val appModule = module {
    //navigation
    single<Cicerone<Router>> { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().navigatorHolder }

    single<ISchedulers> { AppSchedulers() }
    //interactors
    single<ISetupInteractor> { SetupInteractor(get(), get()) }

    //repositories
    single<IAuthRepository> { AuthRepository() }
    single<IProfileRepository> { ProfileRepository(get()) }

    //network
    single { ApiClient.getLogginInterceptor() }
    single { ApiClient.getOkHttpClient(get()) }
    single { ApiClient.getRetrofit(get()) }
    single { ApiClient.getApiService(get()) }

    //Viewmodels
    viewModel { MainViewModel(get(), get()) }
    viewModel { DashboardViewModel(get(), get(), get()) }
    viewModel { CameraViewModel(get(), get(), get()) }
}