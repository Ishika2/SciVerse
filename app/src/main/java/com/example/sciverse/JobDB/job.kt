package com.example.sciverse.JobDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class job(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "jobid") val jobid: String,
    @ColumnInfo(name = "result") val Result: String

)