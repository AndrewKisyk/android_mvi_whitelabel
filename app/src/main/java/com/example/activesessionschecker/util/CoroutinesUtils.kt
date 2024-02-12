/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.activesessionschecker.util

import com.example.activesessionschecker.ui.base.AsyncAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onErrorReturn
import kotlinx.coroutines.flow.onStart

private const val StopTimeoutMillis: Long = 5000

/**
 * A [SharingStarted] meant to be used with a [StateFlow] to expose data to the UI.
 *
 * When the UI stops observing, upstream flows stay active for some time to allow the system to
 * come back from a short-lived configuration change (such as rotations). If the UI stops
 * observing for longer, the cache is kept but the upstream flows are stopped. When the UI comes
 * back, the latest value is replayed and the upstream flows are executed again. This is done to
 * save resources when the app is in the background but let users switch between apps quickly.
 */
val WhileUiSubscribed: SharingStarted = SharingStarted.WhileSubscribed(StopTimeoutMillis)


fun <T : Any> Flow<T>.toAsyncAction(): Flow<AsyncAction<T>> {
    return map { data -> AsyncAction<T>().success(data) }.onStart {
        emit(AsyncAction<T>().loading())
    }.catch { AsyncAction<T>().failure(it) }
}

fun <E, T : Collection<E>> Flow<T>.toCollectionAsyncAction(): Flow<AsyncAction<T>> {
    return map { data ->
        if (data.isEmpty()) {
            AsyncAction<T>().empty()
        } else {
            AsyncAction<T>().success(data)
        }
    }.onStart {
        emit(AsyncAction<T>().loading())
    }.catch { AsyncAction<T>().failure(it) }
}