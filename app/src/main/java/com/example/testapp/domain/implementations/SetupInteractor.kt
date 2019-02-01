package com.example.testapp.domain.implementations

import com.example.testapp.domain.interfaces.ISetupInteractor
import com.example.testapp.domain.model.Profile
import com.example.testapp.mapping.toDomainModel
import com.example.testapp.repository.interfaces.IAuthRepository
import com.example.testapp.repository.interfaces.IProfileRepository
import io.reactivex.Completable
import io.reactivex.Single

class SetupInteractor(
        private val authRepository: IAuthRepository,
        private val profileRepository: IProfileRepository
) : ISetupInteractor {
    override fun doSetup(): Completable = authRepository.isAuthorized()
            .map {
                //todo handle auth state
                Unit
            }.ignoreElement()

    override fun loadProfile(): Single<Profile> = profileRepository.getProfile()
            .map { it.toDomainModel() }
}