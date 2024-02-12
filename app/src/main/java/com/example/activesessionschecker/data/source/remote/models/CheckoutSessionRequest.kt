package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class CheckoutSessionRequest(
    @SerializedName("endTime")
    val endTime: String?
)