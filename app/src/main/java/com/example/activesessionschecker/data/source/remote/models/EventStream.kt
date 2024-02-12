package com.example.activesessionschecker.data.source.remote.models

data class EventStream(
    val active: String,
    val id: String,
    val inactive: String,
    val position: Int
)