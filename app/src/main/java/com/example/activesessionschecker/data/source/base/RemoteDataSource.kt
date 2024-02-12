package com.example.activesessionschecker.data.source.base

import com.example.activesessionschecker.data.base.HandleError

interface RemoteDataSource {

    suspend fun <T> handle(block: suspend () -> T): T

    abstract class Abstract(
        private val handleError: HandleError
    ) : RemoteDataSource {

        override suspend fun <T> handle(block: suspend () -> T): T =
            try {
                block.invoke()
            } catch (error: Exception) {
                throw handleError.handle(error)
            }
    }
}