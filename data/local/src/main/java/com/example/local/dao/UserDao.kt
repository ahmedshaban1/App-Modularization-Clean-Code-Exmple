package com.example.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.User

interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("select * from user limit 1")
    suspend fun getUser(): User

    @Delete
    suspend fun delete(user: User)
}