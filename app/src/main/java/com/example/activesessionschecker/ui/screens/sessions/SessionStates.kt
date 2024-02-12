package com.example.activesessionschecker.ui.screens.sessions

import com.example.activesessionschecker.R
import com.example.activesessionschecker.data.domain.models.ActiveSession
import com.example.activesessionschecker.ui.base.State
import com.example.activesessionschecker.data.source.remote.models.Session

data class SessionUiState(
    val items: List<ActiveSession> = emptyList(),
    val isLoading: Boolean = false,
    val filteringUiInfo: FilteringUiInfo = FilteringUiInfo(),
    val userMessage: Int? = null
): State

data class FilteringUiInfo(
    val currentFilteringLabel: Int = R.string.label_all,
    val noTasksLabel: Int = R.string.no_tasks_all,
    val noTaskIconRes: Int = R.drawable.ic_launcher_foreground,
)

