package com.example.activesessionschecker.data.base

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {

    fun provideRetrofitBuilder(): Retrofit.Builder

    abstract class Abstract(
        private val provideConverterFactory: ProvideConverterFactory,
        private val httpClientBuilder: ProvideOkHttpClientBuilder,
    ) : ProvideRetrofitBuilder {

        override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(provideConverterFactory.converterFactory())
            .client(httpClientBuilder.httpClientBuilder().build())
    }

    class Base(
        provideConverterFactory: ProvideConverterFactory,
        httpClientBuilder: ProvideOkHttpClientBuilder,
    ) : Abstract(
        provideConverterFactory,
        httpClientBuilder
    )
}