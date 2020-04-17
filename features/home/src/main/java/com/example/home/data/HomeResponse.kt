package com.example.home.data

import com.example.model.Category
import com.example.model.Product
import com.google.gson.annotations.SerializedName

data class HomeSection(
    val title: String,
    @SerializedName("view_type") var viewType: String,
    var categories: MutableList<Category>? = mutableListOf(),
    var products: MutableList<Product>? = mutableListOf()
)