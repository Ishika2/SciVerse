package com.example.sciverse.JobDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [job::class], version = 1, exportSchema = false)
abstract class jobRoomDatabase:RoomDatabase() {
    abstract fun dao(): jobDao

    companion object {
        @Volatile
        private var INSTANCE: jobRoomDatabase? = null

        fun getDatabase(context: Context): jobRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    jobRoomDatabase::class.java,
                    "job_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}