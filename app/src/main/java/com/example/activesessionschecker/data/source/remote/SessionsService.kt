package com.example.activesessionschecker.data.source.remote


import com.example.activesessionschecker.data.source.remote.models.CheckoutSessionRequest
import com.example.activesessionschecker.data.source.remote.models.CheckoutSessionResponse
import com.example.activesessionschecker.data.source.remote.models.SessionProduct
import com.example.activesessionschecker.data.source.remote.models.SessionResponse
import com.example.activesessionschecker.data.source.remote.models.SessionResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface SessionsService {
    fun currencyService(): SessionsService
    @GET("api/v1/session")
    suspend fun getAllSessions(): SessionResponse

    @GET("api/v1/session/{id}")
    suspend fun getSession(@Path("id") id: String): SessionResponseItem

    @GET("api/v1/session/{id}/products")
    suspend fun getSessionProducts(@Path("id") id: String): List<SessionProduct>

    @PATCH("api/v1/session/{id}")
    suspend fun checkoutSession(
        @Path("id") id: String,
        @Body checkoutSessionRequest: CheckoutSessionRequest
    ): CheckoutSessionResponse
}