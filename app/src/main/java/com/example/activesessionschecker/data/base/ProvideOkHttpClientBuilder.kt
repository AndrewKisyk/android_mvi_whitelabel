package com.example.activesessionschecker.data.base

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun httpClientBuilder(): OkHttpClient.Builder

    abstract class Abstract(
        private val provideInterceptor: ProvideInterceptor,
        private val timeOutSeconds: Long = 60L
    ) : ProvideOkHttpClientBuilder {

        override fun httpClientBuilder() = OkHttpClient.Builder()
            .addInterceptor(provideInterceptor.interceptor())
            .connectTimeout(timeOutSeconds, TimeUnit.SECONDS)
            .readTimeout(timeOutSeconds, TimeUnit.SECONDS)
    }

    class Base(
        provideInterceptor: ProvideInterceptor,
    ) : Abstract(provideInterceptor)
}