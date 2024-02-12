package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class SessionProduct(
    @SerializedName("confidence")
    val confidence: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("modelUsed")
    val modelUsed: String,
    @SerializedName("productSku")
    val productSku: String,
    @SerializedName("price")
    val price: Double?=null
)