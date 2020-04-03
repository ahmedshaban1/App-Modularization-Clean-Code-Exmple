package com.example.home.data

import com.example.model.Category
import com.example.model.Product
import com.google.gson.annotations.SerializedName

data class HomeSection(
    val title: String,
    @SerializedName("view_type") val viewType: String,
    var categories: List<Category>? = null,
    var products: List<Product>? = null
)