package com.example.activesessionschecker.di

import com.example.activesessionschecker.data.domain.ActiveSessionsRepository
import com.example.activesessionschecker.data.domain.ActiveSessionsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindActiveSessionsRepository(repository: ActiveSessionsRepositoryImpl): ActiveSessionsRepository
}