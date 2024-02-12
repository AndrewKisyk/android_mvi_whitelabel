package com.example.activesessionschecker.ui.base
import kotlinx.coroutines.flow.Flow

fun interface SideEffect : Action {
    fun run(): Flow<Action>
}