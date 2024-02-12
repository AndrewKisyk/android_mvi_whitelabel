package com.example.activesessionschecker.ui.screens.sessions

import com.example.activesessionschecker.data.domain.ActiveSessionsRepository
import com.example.activesessionschecker.data.domain.models.ActiveSession
import com.example.activesessionschecker.ui.base.Action
import com.example.activesessionschecker.ui.base.AsyncAction
import com.example.activesessionschecker.ui.base.SideEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

sealed interface SessionActions : Action {

    object ActiveSessionsAsync : SessionActions, AsyncAction<List<ActiveSession>>()

    object ActiveSessionAsync : SessionActions, AsyncAction<ActiveSession>()
}

sealed interface SessionEffects : SessionActions, SideEffect {

    fun apply(activeSessionsRepository: ActiveSessionsRepository): SessionEffects

    open class FetchActiveSessionsEffect : SessionEffects {
        override fun apply(activeSessionsRepository: ActiveSessionsRepository): SessionEffects {
            return Applied(activeSessionsRepository)
        }

        class Applied(private val activeSessionsRepository: ActiveSessionsRepository) :
            FetchActiveSessionsEffect() {
            override fun run(): Flow<SessionActions.ActiveSessionsAsync> =
                activeSessionsRepository.getActiveSessions(true)
                    .map { SessionActions.ActiveSessionsAsync.apply { copyStatusFrom(it) } }
        }

        override fun run(): Flow<Action> = flow {}
    }
}