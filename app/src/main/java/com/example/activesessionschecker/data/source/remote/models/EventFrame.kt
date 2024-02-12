package com.example.activesessionschecker.data.source.remote.models

data class EventFrame(
    val classificationEventId: String,
    val eventStream: EventStream,
    val eventStreamId: String,
    val id: String,
    val image: String,
    val time: String
)