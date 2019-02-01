package com.example.testapp.repository.implementations

import com.example.testapp.repository.interfaces.IAuthRepository
import io.reactivex.Single

class AuthRepository : IAuthRepository {
    override fun isAuthorized(): Single<Boolean> = Single.just(false)
}
