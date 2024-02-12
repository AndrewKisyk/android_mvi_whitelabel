package com.example.activesessionschecker.data.domain

import com.example.activesessionschecker.data.domain.models.ActiveSession
import com.example.activesessionschecker.ui.base.AsyncAction
import kotlinx.coroutines.flow.Flow


interface ActiveSessionsRepository  {

    fun getActiveSessionsStream(): Flow<AsyncAction<List<ActiveSession>>>

    fun getActiveSessions(forceUpdate: Boolean = false): Flow<AsyncAction<List<ActiveSession>>>

    suspend fun refresh()

    fun getActiveSessionStream(activeSessionId: String): Flow<AsyncAction<ActiveSession>>

    suspend fun getActiveSession(activeSessionId: String, forceUpdate: Boolean = false): ActiveSession?

    suspend fun refreshActiveSession(activeSessionId: String)

    suspend fun deleteAllActiveSessions()
}