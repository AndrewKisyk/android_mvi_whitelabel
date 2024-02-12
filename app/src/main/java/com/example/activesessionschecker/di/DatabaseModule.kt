package com.example.activesessionschecker.di

import android.content.Context
import androidx.room.Room
import com.example.activesessionschecker.data.source.local.ActiveSessionDao
import com.example.activesessionschecker.data.source.local.ActiveSessionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ActiveSessionDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ActiveSessionDatabase::class.java,
            "ActiveSession.db"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: ActiveSessionDatabase): ActiveSessionDao = database.activeSessionDao()
}
