package com.example.activesessionschecker.ui.base


interface Action {
    fun isComplete() = false
    object Init : Action
    object Replace : Action
}
