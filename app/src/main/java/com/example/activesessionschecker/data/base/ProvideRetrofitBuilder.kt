package com.example.activesessionschecker.data.base

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

interface ProvideRetrofitBuilder {

    fun provideRetrofitBuilder(): Retrofit.Builder

    abstract class Abstract(
        private val converterFactory: Converter.Factory,
        private val httpClientBuilder: OkHttpClient.Builder,
    ) : ProvideRetrofitBuilder {

        override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(httpClientBuilder.build())
    }

    class Base(
        converterFactory: Converter.Factory,
        httpClientBuilder: OkHttpClient.Builder,
    ) : Abstract(
        converterFactory,
        httpClientBuilder
    )
}