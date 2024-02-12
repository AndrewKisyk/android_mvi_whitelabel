package com.example.activesessionschecker.base


interface Matches<T> {

    fun matches(data: T): Boolean
}