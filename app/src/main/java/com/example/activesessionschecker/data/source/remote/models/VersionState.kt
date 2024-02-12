package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class VersionState(
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("test")
    val test: String? = null
)