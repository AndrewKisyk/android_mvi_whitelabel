package com.example.activesessionschecker.base


abstract class Mapper<T, F> {

    abstract suspend fun mapTo(item: F): T

    open suspend fun mapTo(items: List<F>): List<T> {
        return items.map { mapTo(it) }
    }

}
