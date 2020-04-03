package com.example.model

import com.google.gson.annotations.SerializedName

data class Category(@SerializedName("updated_at")
                       var updatedAt: String? = null,
                       @SerializedName("image_url")
                       var imageUrl: String? = null,
                       @SerializedName("name")
                       var name: String = "",
                       @SerializedName("created_at")
                       var createdAt: String? = null,
                       @SerializedName("id")
                       var id: Int = 0)