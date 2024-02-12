package com.example.activesessionschecker.data.domain

import com.example.activesessionschecker.data.domain.mappers.activesessions.ActiveSessionEntityMapper
import com.example.activesessionschecker.data.domain.mappers.activesessions.ActiveSessionResponseToEntityMapper
import com.example.activesessionschecker.data.domain.models.ActiveSession
import com.example.activesessionschecker.data.source.local.ActiveSessionDao
import com.example.activesessionschecker.data.source.remote.SessionsRemoteDataSource
import com.example.activesessionschecker.di.ApplicationScope
import com.example.activesessionschecker.di.DefaultDispatcher
import com.example.activesessionschecker.ui.base.AsyncAction
import com.example.activesessionschecker.util.toAsyncAction
import com.example.activesessionschecker.util.toCollectionAsyncAction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActiveSessionsRepositoryImpl @Inject constructor(
    private val remoteDataSource: SessionsRemoteDataSource,
    private val localDataSource: ActiveSessionDao,
    private val activeSessionEntityMapper: ActiveSessionEntityMapper,
    private val activesSessionResponseToEntityMapper: ActiveSessionResponseToEntityMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
) : ActiveSessionsRepository {

    override fun getActiveSessionsStream(): Flow<AsyncAction<List<ActiveSession>>> {
        return localDataSource.observeAll()
            .map { session ->
                withContext(dispatcher) {
                    activeSessionEntityMapper.mapTo(
                        session
                    )
                }
            }.toCollectionAsyncAction()
    }

    override fun getActiveSessions(forceUpdate: Boolean) = flow {
        if (forceUpdate) {
            refresh()
        }
        emit(activeSessionEntityMapper.mapTo(localDataSource.getAll()))
    }.toCollectionAsyncAction()

    override suspend fun refresh() {
        withContext(dispatcher) {
            val remoteSessions = remoteDataSource.loadSessions()
            localDataSource.deleteAll()
            localDataSource.upsertAll(activesSessionResponseToEntityMapper.mapTo(remoteSessions))
        }
    }

    override fun getActiveSessionStream(activeSessionId: String): Flow<AsyncAction<ActiveSession>> {
        return localDataSource.observeById(activeSessionId)
            .map(activeSessionEntityMapper::mapTo)
            .toAsyncAction()
    }

    override suspend fun getActiveSession(
        activeSessionId: String,
        forceUpdate: Boolean
    ): ActiveSession? {
        if (forceUpdate) {
            refresh()
        }
        return localDataSource.getById(activeSessionId)
            ?.run { activeSessionEntityMapper.mapTo(this) }
    }

    override suspend fun refreshActiveSession(activeSessionId: String) {
        refresh()
    }

    override suspend fun deleteAllActiveSessions() {
        localDataSource.deleteAll()
    }
}



