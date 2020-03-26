package com.example.splash.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dao.BlogPostDao
import com.example.model.BlogPostApi
import com.example.remote.data.Resource
import com.example.splash.data.SplashRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.safeApiCall


class SplashViewModel(val splashRepository: SplashRepository) : ViewModel() {


    private val _posts = MutableLiveData<Resource<List<BlogPostApi>>>()
    val postsLD: LiveData<Resource<List<BlogPostApi>>> get() = _posts
    fun getBlogPosts() {
        viewModelScope.launch {
            splashRepository.getPosts().collect { data ->
                _posts.value = data
            }
        }

    }
}