package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(@SerializedName("updated_at")
                val updatedAt: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("created_at")
                val createdAt: String = "",
                @PrimaryKey
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("email")
                val email: String = "",
                @SerializedName("token")
                val token: String = "")