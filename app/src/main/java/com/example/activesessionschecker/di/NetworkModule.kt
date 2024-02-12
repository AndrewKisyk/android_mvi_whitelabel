package com.example.activesessionschecker.di

import com.example.activesessionschecker.BuildConfig
import com.example.activesessionschecker.data.base.HandleError
import com.example.activesessionschecker.data.domain.base.HandleDomainError
import com.example.activesessionschecker.data.source.remote.SessionsService
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class SessionApiNetworking

    @Provides
    @Named("logging")
    @Singleton
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create()
        )
    }

    @Provides
    @SessionApiNetworking
    @Singleton
    fun providesOkHttpClient(
        @Named("logging") loggingInterceptor: Interceptor,
        converterFactory: Converter.Factory,
    ): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT_SECONDS_CONNECT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT_SECONDS_READ, TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT_SECONDS_WRITE, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesSessionsServiceRetrofit(
        @SessionApiNetworking okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SESSIONS_API_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class HandleDomain

    @Provides
    @NetworkModule.HandleDomain
    @Singleton
    fun providesHandleDomain(): HandleError {
        return HandleDomainError()
    }

    @Provides
    @Singleton
    fun providesSessionsService(retrofit: Retrofit): SessionsService {
        return retrofit.create(SessionsService::class.java)
    }
}