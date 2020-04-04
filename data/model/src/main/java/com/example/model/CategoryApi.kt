package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
data class Category(
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("image_url")
    var imageUrl: String? = null,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("created_at")
    var createdAt: String? = null,
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0
)