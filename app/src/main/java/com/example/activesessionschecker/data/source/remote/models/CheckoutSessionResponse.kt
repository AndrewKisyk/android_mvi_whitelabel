package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class CheckoutSessionResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("cartId")
    val cartId: Int?,
    @SerializedName("endTime")
    val endTime: String?,
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("startTime")
    val startTime: String?,
    @SerializedName("versionState")
    val versionState: VersionState?
)