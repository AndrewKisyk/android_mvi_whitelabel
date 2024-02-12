package com.example.activesessionschecker.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.activesessionschecker.data.source.local.models.ActiveSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActiveSessionDao {
    /**
     * Observes list of activeSessions.
     *
     * @return all activeSessions.
     */
    @Query("SELECT * FROM activeSessions")
    fun observeAll(): Flow<List<ActiveSessionEntity>>

    /**
     * Observes a single task.
     *
     * @param activeSessionId the task id.
     * @return the task with activeSessionId.
     */
    @Query("SELECT * FROM activeSessions WHERE id = :activeSessionId")
    fun observeById(activeSessionId: String): Flow<ActiveSessionEntity>

    /**
     * Select all activeSessions from the activeSessions table.
     *
     * @return all activeSessions.
     */
    @Query("SELECT * FROM activeSessions")
    suspend fun getAll(): List<ActiveSessionEntity>

    /**
     * Select a task by id.
     *
     * @param activeSessionId the task id.
     * @return the task with activeSessionId.
     */
    @Query("SELECT * FROM activeSessions WHERE id = :activeSessionId")
    suspend fun getById(activeSessionId: String): ActiveSessionEntity?

    /**
     * Insert or update a task in the database. If a task already exists, replace it.
     *
     * @param task the task to be inserted or updated.
     */
    @Upsert
    suspend fun upsert(task: ActiveSessionEntity)

    /**
     * Insert or update activeSessions in the database. If a task already exists, replace it.
     *
     * @param activeSessions the activeSessions to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAll(activeSessions: List<ActiveSessionEntity>)


    /**
     * Delete a task by id.
     *
     * @return the number of activeSessions deleted. This should always be 1.
     */
    @Query("DELETE FROM activeSessions WHERE id = :activeSessionId")
    suspend fun deleteById(activeSessionId: String): Int

    /**
     * Delete all activeSessions.
     */
    @Query("DELETE FROM activeSessions")
    suspend fun deleteAll()
}