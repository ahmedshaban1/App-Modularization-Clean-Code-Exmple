package com.example.home.data.datasource.remote

import com.example.home.data.HomeSection
import com.example.model.BlogPostApi

interface HomeRemote {
    suspend fun getHome(): List<HomeSection>
}