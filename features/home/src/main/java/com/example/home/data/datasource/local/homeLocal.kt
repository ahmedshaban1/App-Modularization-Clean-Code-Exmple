package com.example.home.data.datasource.local

import com.example.home.data.HomeSection

interface HomeLocal {
    suspend fun getHome(): List<HomeSection>
    suspend fun saveHomeData(data: List<HomeSection>)

}