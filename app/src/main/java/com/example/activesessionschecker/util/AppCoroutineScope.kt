package com.example.activesessionschecker.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import java.util.concurrent.CancellationException

object AppCoroutineScope : CoroutineScope {
    override val coroutineContext = Dispatchers.Main + Job()

    fun cancel() {
        coroutineContext.cancel(CancellationException("App Destroyed"))
    }

}