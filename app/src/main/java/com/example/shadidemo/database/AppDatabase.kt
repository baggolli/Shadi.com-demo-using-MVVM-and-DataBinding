package com.example.shadidemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shadidemo.database.dao.UserDao
import com.example.shadidemo.database.entity.UserEntity

@Database(entities = [(UserEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
}