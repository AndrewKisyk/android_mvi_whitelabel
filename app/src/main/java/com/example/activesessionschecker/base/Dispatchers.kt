package com.example.activesessionschecker.base

import kotlinx.coroutines.*

interface Dispatchers {

    fun launchUI(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

    fun launchBackground(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

    suspend fun changeToUI(block: suspend CoroutineScope. () -> Unit)

    abstract class Abstract(
        private val ui: CoroutineDispatcher,
        private val background: CoroutineDispatcher,
    ) : Dispatchers {

        override fun launchUI(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ): Job = scope.launch(ui, block = block)

        override fun launchBackground(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ): Job = scope.launch(background, block = block)

        override suspend fun changeToUI(block: suspend CoroutineScope. () -> Unit) =
            withContext(ui, block)
    }

    class Base : Abstract(kotlinx.coroutines.Dispatchers.Main, kotlinx.coroutines.Dispatchers.IO)
}