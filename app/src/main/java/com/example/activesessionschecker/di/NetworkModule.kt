package com.example.activesessionschecker.di


import com.example.activesessionschecker.BuildConfig
import com.example.activesessionschecker.data.base.HandleError
import com.example.activesessionschecker.data.base.ProvideConverterFactory
import com.example.activesessionschecker.data.base.ProvideInterceptor
import com.example.activesessionschecker.data.base.ProvideOkHttpClientBuilder
import com.example.activesessionschecker.data.base.ProvideRetrofitBuilder
import com.example.activesessionschecker.data.base.ServiceProvider
import com.example.activesessionschecker.data.domain.base.HandleDomainError
import com.example.activesessionschecker.data.source.remote.SessionsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
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
    fun providesLoggingInterceptor(): ProvideInterceptor {
        return if (BuildConfig.DEBUG) {
            ProvideInterceptor.Debug()
        } else {
            ProvideInterceptor.Release()
        }
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): Converter.Factory {
        return ProvideConverterFactory.Base().converterFactory()
    }

    @Provides
    @Singleton
    fun providesOkHttpClientBuilder(
        @Named("logging") loggingInterceptor: ProvideInterceptor
    ): OkHttpClient.Builder {
        return ProvideOkHttpClientBuilder.Base(loggingInterceptor).httpClientBuilder()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient.Builder
    ): Retrofit.Builder {
        return ProvideRetrofitBuilder.Base(converterFactory, okHttpClient).provideRetrofitBuilder()
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
    fun providesSessionsService(retrofitBuilder: Retrofit.Builder): SessionsService {
        return ServiceProvider.Base(retrofitBuilder, BuildConfig.WEATHER_API_BASE_URL)
            .service(SessionsService::class.java)
    }
}