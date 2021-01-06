package com.example.shadidemo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shadidemo.database.entity.UserEntity

@Dao
abstract class UserDao {
    @Query("SELECT * FROM User ORDER BY firstName ASC")
    abstract fun getAllUsers(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllUsers(userList: List<UserEntity>?)

    @Query("UPDATE User SET status = :isAccepted WHERE id =:id")
    abstract fun updateUser(isAccepted: Int?, id: Int)

    @Query("DELETE FROM User")
    abstract fun deleteAllUsers()
}