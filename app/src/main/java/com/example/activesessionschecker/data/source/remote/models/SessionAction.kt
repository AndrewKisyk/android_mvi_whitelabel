package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class SessionAction(
    @SerializedName("actionType")
    val actionType: String,
    @SerializedName("classificationResult")
    val classificationResult: ClassificationResult,
    @SerializedName("classificationResultId")
    val classificationResultId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("sessionId")
    val sessionId: String,
    @SerializedName("sessionProduct")
    val sessionProduct: SessionProduct,
    @SerializedName("sessionProductId")
    val sessionProductId: Int
)

fun buildSessionActionPreview() = SessionAction(
    "action type", getClassificationResult(), "classificationResultId",
    "id", "sessionId", getSessionProduct(), 1
)

fun getEventStream() = EventStream(
    "active",
    "id",
    "inactive",
    1
)

fun getEventFrame() = EventFrame(
    "classificationEventId",
    getEventStream(),
    "eventStreamId", "id",
    "image",
    "time"
)

fun getClassificationEvent() = ClassificationEvent(listOf(getEventFrame()), "id")

fun getClassificationResult() = ClassificationResult(
    "barcode", listOf(getCandidate()),
    getClassificationEvent(),
    "classificationEventId",
    2.0,
    "description",
    "id", "tag"
)

fun getCandidate() = Candidate("key", "str")

fun getMetadata() = Metadata("key", "name")

fun getSessionProduct() = SessionProduct(2.0, 2, getMetadata(), "modelUsed", "productSku", 1.0)
