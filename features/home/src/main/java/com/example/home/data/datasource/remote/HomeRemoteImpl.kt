package com.example.home.data.datasource.remote

import com.example.home.data.HomeSection
import com.example.home.data.HomeServices

class HomeRemoteImpl(private val remote: HomeServices) : HomeRemote {
    override suspend fun getHome(): List<HomeSection> {
        return remote.getHome()
    }
}