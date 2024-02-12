package com.example.activesessionschecker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.example.activesessionschecker.ui.base.State
import com.example.activesessionschecker.ui.base.loggerMiddleware
import com.example.activesessionschecker.ui.base.sideEffectMiddleware
import com.example.activesessionschecker.ui.screens.sessions.SessionUiState
import com.example.activesessionschecker.ui.screens.sessions.activeSessionReducer
import kotlinx.coroutines.CoroutineScope
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.threadsafe.createTypedThreadSafeStore


@Composable
fun AppStore(coroutineScope: CoroutineScope, content: @Composable () -> Unit) = StoreProvider(
    createTypedThreadSafeStore(
        ::rootReducer,
        AppState(),
        applyMiddleware(
            sideEffectMiddleware(coroutineScope),
            loggerMiddleware()
        )
    )
) {
    content()
}

fun rootReducer(state: AppState, action: Any) = AppState(
    activeSessions = activeSessionReducer(state.activeSessions, action),
)

@Immutable
data class AppState(
    val activeSessions: SessionUiState = SessionUiState()
): State


