package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class ClassificationResult(
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("candidates")
    val candidates: List<Candidate>,
    @SerializedName("classificationEvent")
    val classificationEvent: ClassificationEvent,
    @SerializedName("classificationEventId")
    val classificationEventId: String,
    @SerializedName("confidence")
    val confidence: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("tag")
    val tag: String
)