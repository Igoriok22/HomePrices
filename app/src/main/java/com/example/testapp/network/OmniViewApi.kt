package com.example.testapp.network

import com.example.testapp.domain.model.CurrentExchangeRate

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OmniViewApi {
    @GET("/api/v5/convert?compact=y")
    fun getExchangeRate(@Query("q") query: String): Single<CurrentExchangeRate>
}