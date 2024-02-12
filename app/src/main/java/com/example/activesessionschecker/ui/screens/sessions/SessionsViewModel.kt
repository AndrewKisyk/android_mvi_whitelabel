package com.example.activesessionschecker.ui.screens.sessions

import com.example.activesessionschecker.base.BaseViewModel
import com.example.activesessionschecker.data.domain.ActiveSessionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.reduxkotlin.TypedDispatcher
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor(private val sessionsRepository: ActiveSessionsRepository) : BaseViewModel<SessionEffects>() {
    override fun dispatchEffect(
        effect: SessionEffects,
        dispatcher: TypedDispatcher<SessionEffects>
    ) {
        dispatcher(effect.apply(sessionsRepository))
    }
}