package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class Product(
    @SerializedName("isMastVisited")
    var isMastVisited: Boolean? = false,
    @SerializedName("reviews")
    var reviews: Int? = 0,
    @SerializedName("price")
    var price: String? = "",
    @SerializedName("image_url")
    var imageUrl: String? = "",
    @SerializedName("isBestSell")
    var isBestSell: Boolean? = false,
    @SerializedName("isMastRated")
    var isMastRated: Boolean? = false,
    @SerializedName("cat_id")
    var catId: Int? = 0,
    @SerializedName("isRecommended")
    var isRecommended: Boolean? = false,
    @SerializedName("description")
    var description: String? = "",
    @PrimaryKey
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("isFeatured")
    var isFeatured: Boolean? = false
)