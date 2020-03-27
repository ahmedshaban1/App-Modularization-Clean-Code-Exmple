package com.example.splash.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dao.BlogPostDao
import com.example.model.BlogPost
import com.example.model.BlogPostApi
import com.example.remote.data.Resource
import com.example.splash.data.SplashRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.safeApiCall
import com.example.splash.domain.GetBlogPostsUseCase


class SplashViewModel(val useCase: GetBlogPostsUseCase) : ViewModel() {


    private val _posts = MutableLiveData<Resource<List<BlogPost>>>()
    val postsLD: LiveData<Resource<List<BlogPost>>> get() = _posts
    fun getBlogPosts() {
        viewModelScope.launch {
            useCase.getPosts().collect { data ->
                _posts.value = data
            }
        }

    }
}