package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "BlogPosts")
data class BlogPostApi(@PrimaryKey val pk: String, val title: String)


data class BlogPost(val pk: String, val title: String)