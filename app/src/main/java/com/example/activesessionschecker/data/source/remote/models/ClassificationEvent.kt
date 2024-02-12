package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class ClassificationEvent(
    @SerializedName("eventFrames")
    val eventFrames: List<EventFrame>,
    @SerializedName("id")
    val id: String
)