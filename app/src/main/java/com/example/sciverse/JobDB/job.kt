package com.example.sciverse.JobDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.checkerframework.checker.nullness.qual.NonNull

@Entity(tableName = "jobs")
data class job(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "jobid") val jobid: String,
    @ColumnInfo(name = "result") val Result: String

)