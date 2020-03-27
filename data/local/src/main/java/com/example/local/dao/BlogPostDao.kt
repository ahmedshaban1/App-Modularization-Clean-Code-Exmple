package com.example.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.BlogPost
import com.example.model.BlogPostApi

@Dao
interface BlogPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogPost: BlogPostApi):Long

    @Query("SELECT * FROM blogposts")
    suspend fun getAllPosts() : List<BlogPostApi>
}