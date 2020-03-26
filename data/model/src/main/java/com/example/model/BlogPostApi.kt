package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "BlogPosts")
data class BlogPost(@PrimaryKey val pk: String, val title: String)


data class BlogPostApi(val pk: String, val title: String)