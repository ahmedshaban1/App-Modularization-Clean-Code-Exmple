package com.example.local.dao

import androidx.room.*
import com.example.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("select * from user limit 1")
    suspend fun getUser(): User

    @Delete
    suspend fun delete(user: User)
}