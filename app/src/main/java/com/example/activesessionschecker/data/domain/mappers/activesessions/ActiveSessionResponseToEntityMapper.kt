package com.example.activesessionschecker.data.domain.mappers.activesessions
import com.example.activesessionschecker.base.Mapper

import com.example.activesessionschecker.data.source.local.models.ActiveSessionEntity
import com.example.activesessionschecker.data.source.remote.models.SessionResponseItem
import javax.inject.Inject

class ActiveSessionResponseToEntityMapper @Inject constructor() : Mapper<ActiveSessionEntity, SessionResponseItem>() {
    override suspend fun mapTo(item: SessionResponseItem): ActiveSessionEntity {
       return ActiveSessionEntity(item.id, item.id)
    }
}
