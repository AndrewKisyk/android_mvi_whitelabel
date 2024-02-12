package com.example.activesessionschecker.data.source.remote

import com.example.activesessionschecker.data.base.HandleError
import com.example.activesessionschecker.data.source.remote.models.SessionResponse
import com.example.activesessionschecker.data.source.remote.models.SessionResponseItem
import com.example.activesessionschecker.data.source.base.RemoteDataSource
import com.example.activesessionschecker.di.NetworkModule
import javax.inject.Inject

interface SessionsRemoteDataSource {

    suspend fun loadSessions(): List<SessionResponseItem>

    class SessionsRemoteDataSourceImpl @Inject constructor(
        private val sessionService: SessionsService,
        @NetworkModule.HandleDomain handleError: HandleError
    ) : RemoteDataSource.Abstract(handleError), SessionsRemoteDataSource {
        override suspend fun loadSessions() = handle {
            sessionService.getAllSessions()
        }
    }
}