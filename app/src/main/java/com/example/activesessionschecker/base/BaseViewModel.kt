package com.example.activesessionschecker.base

import androidx.lifecycle.ViewModel
import com.example.activesessionschecker.ui.base.SideEffect
import org.reduxkotlin.TypedDispatcher

abstract class BaseViewModel<A : SideEffect> : ViewModel(), EffectDispatcher<A> {
    override fun dispatchEffect(effect: A, dispatcher: TypedDispatcher<A>) {
        dispatcher(effect)
    }
}

interface EffectDispatcher<A : SideEffect> {
    fun dispatchEffect(effect: A, dispatcher: TypedDispatcher<A>)
}