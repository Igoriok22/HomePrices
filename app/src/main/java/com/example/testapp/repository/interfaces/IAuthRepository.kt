package com.example.testapp.repository.interfaces

import io.reactivex.Single

interface IAuthRepository {
    fun isAuthorized(): Single<Boolean>
}