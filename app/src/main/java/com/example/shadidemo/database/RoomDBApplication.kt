package com.example.shadidemo.database

import android.app.Application
import androidx.room.Room

class RoomDBApplication : Application() {
    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}