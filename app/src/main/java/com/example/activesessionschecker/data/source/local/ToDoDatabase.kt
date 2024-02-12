package com.example.activesessionschecker.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.activesessionschecker.data.source.local.models.ActiveSessionEntity

@Database(entities = [ActiveSessionEntity::class], version = 1, exportSchema = false)
abstract class ActiveSessionDatabase : RoomDatabase() {

    abstract fun activeSessionDao(): ActiveSessionDao
}
