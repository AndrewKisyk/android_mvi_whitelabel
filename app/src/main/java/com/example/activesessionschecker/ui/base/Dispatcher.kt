package com.example.activesessionschecker.ui.base

import androidx.compose.runtime.Composable
import com.example.activesessionschecker.base.EffectDispatcher
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.compose.rememberStore

class ViewModelDispatcher<S : SideEffect>(private val effectDispatcher: EffectDispatcher<S>, private val dispatcher: Dispatcher) {

    operator fun invoke(action: Any) = dispatcher(action)

    operator fun invoke(action: S) {
        effectDispatcher.dispatchEffect(action, dispatcher)
    }
}

@Composable
public fun <S : SideEffect> rememberDispatcher(effectDispatcher: EffectDispatcher<S>): ViewModelDispatcher<S> {
    return ViewModelDispatcher(effectDispatcher, rememberStore<Any>().dispatch)
}
