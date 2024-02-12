package com.example.activesessionschecker.ui.screens.sessions

import com.example.activesessionschecker.R
import com.example.activesessionschecker.ui.base.AsyncStatus
import org.reduxkotlin.typedReducer

val activeSessionReducer = typedReducer<SessionUiState, SessionActions> { state, action ->
    when (action) {
        is SessionActions.ActiveSessionsAsync -> when (action.status) {
            is AsyncStatus.Loading -> state.copy(isLoading = true)
            is AsyncStatus.Success -> state.copy(
                isLoading = false,
                userMessage = null,
                items = action.data
            )

            is AsyncStatus.Failure -> state.copy(
                isLoading = false,
                userMessage = R.string.error_message
            )

            is AsyncStatus.Empty -> state.copy(
                isLoading = false,
                userMessage = null,
                filteringUiInfo = FilteringUiInfo(),
            )
        }

        else -> state
    }
}