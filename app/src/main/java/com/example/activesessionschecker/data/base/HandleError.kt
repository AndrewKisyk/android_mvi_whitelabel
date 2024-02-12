package com.example.activesessionschecker.data.base


interface HandleError {
    fun handle(error: Exception): Exception
}