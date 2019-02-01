package com.example.testapp.repository.implementations

import com.example.testapp.network.OmniViewApi
import com.example.testapp.repository.interfaces.IProfileRepository
import com.example.testapp.repository.model.Profile
import io.reactivex.Single

class ProfileRepository(
        private val api: OmniViewApi
) : IProfileRepository {
    override fun getProfile(): Single<Profile> = api.getProfile()
}