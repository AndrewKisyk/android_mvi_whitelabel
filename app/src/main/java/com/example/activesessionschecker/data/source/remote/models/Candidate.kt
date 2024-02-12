package com.example.activesessionschecker.data.source.remote.models

import com.google.gson.annotations.SerializedName

data class Candidate(
    @SerializedName("key")
    val key: String,
    @SerializedName("str")
    val str: String
)