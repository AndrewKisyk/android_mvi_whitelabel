package com.example.activesessionschecker.ui.base

sealed interface AsyncStatus<T : Any> {
    class Loading<T : Any> : AsyncStatus<T>
    class Success<T : Any>(var data: T) : AsyncStatus<T>
    class Empty<T : Any> : AsyncStatus<T>
    class Failure<T : Any>(var error: String) : AsyncStatus<T>
}