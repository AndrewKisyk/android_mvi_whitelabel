package com.example.activesessionschecker.data.source.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "activeSessions"
)
data class ActiveSessionEntity (
    @PrimaryKey
    val id: String,
    var title: String
)