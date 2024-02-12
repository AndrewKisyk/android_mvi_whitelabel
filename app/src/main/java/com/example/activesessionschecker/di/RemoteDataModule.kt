package com.example.activesessionschecker.di

import com.example.activesessionschecker.data.source.remote.SessionsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataModule {
    @Binds
    abstract fun bindSessionsRemoteDataSource(source: SessionsRemoteDataSource.SessionsRemoteDataSourceImpl): SessionsRemoteDataSource
}