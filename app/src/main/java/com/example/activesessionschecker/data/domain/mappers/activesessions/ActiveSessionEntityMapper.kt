package com.example.activesessionschecker.data.domain.mappers.activesessions

import com.example.activesessionschecker.base.Mapper
import com.example.activesessionschecker.data.domain.models.ActiveSession
import com.example.activesessionschecker.data.source.local.models.ActiveSessionEntity
import javax.inject.Inject

class ActiveSessionEntityMapper @Inject constructor() : Mapper<ActiveSession, ActiveSessionEntity>() {
    override suspend fun mapTo(item: ActiveSessionEntity): ActiveSession {
        return ActiveSession(item.id, item.title)
    }
}