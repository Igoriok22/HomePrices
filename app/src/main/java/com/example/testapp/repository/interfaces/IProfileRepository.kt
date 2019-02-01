package com.example.testapp.repository.interfaces

import com.example.testapp.repository.model.Profile
import io.reactivex.Single

interface IProfileRepository {
    fun getProfile(): Single<Profile>
}