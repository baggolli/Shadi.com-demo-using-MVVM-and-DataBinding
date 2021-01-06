package com.example.shadidemo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val state: String,
    val city: String,
    val country: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var userImage: ByteArray? = null,
    var status: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}