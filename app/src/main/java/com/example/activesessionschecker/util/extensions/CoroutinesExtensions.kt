package com.ghost.maps.util.extensions

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchWhenCreated(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenCreated {
        this@launchWhenCreated.collect()
    }
}

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

fun <T> Flow<T>.launchWhenResumed(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenResumed {
        this@launchWhenResumed.collect()
    }
}
