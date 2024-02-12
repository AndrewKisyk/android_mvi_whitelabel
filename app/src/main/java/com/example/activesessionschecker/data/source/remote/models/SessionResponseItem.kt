package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class SessionResponseItem(
    val id: String,
    @SerializedName("cartId")
    val cartId: Int? = null,
    @SerializedName("endTime")
    val endTime: String? = null,
    @SerializedName("guid")
    val guid: String? = null,
    @SerializedName("sessionActions")
    val sessionActions: List<SessionAction>,
    @SerializedName("startTime")
    val startTime: String? = null,
    @SerializedName("versionState")
    val versionState: VersionState? = null
)

fun buildSessionPreview() = SessionResponseItem(
    id = "51234843",
    cartId = 2,
    endTime = "3.4",
    guid = "",
    sessionActions = emptyList(),
    startTime = "",
    versionState = VersionState("key", "test")
)
