package com.example.activesessionschecker.data.base

import retrofit2.Retrofit


interface ServiceProvider {

    fun <T> service(clasz: Class<T>): T

    class Base(
        private val retrofitBuilder: Retrofit.Builder,
        private val baseUrl: String
    ) : ServiceProvider {

        private val retrofit by lazy {
            retrofitBuilder.baseUrl(baseUrl)
                .build()
        }

        override fun <T> service(clasz: Class<T>): T = retrofit.create(clasz)
    }
}