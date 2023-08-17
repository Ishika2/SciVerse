package com.example.sciverse.JobDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface jobDao {
    @Query("SELECT * FROM jobs ORDER BY id ASC")
    fun getAll(): List<job>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(job: job)

    @Update
    suspend fun update(job: job)

    @Delete
    suspend fun delete(job: job)
}