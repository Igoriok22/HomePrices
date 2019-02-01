package com.example.testapp.network

import com.example.testapp.repository.model.Profile
import io.reactivex.Single
import retrofit2.http.GET

interface OmniViewApi {
    @GET("/bins/b8yig")
    fun getProfile(): Single<Profile>
}