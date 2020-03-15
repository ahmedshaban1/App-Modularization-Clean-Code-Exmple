package com.example.splash.presenter

import android.util.Log
import androidx.lifecycle.*
import com.example.remote.data.Resource
import com.example.splash.data.BlogPost
import com.example.splash.data.SplashRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SplashViewModel(val splashRepository: SplashRepository) : ViewModel() {

    private val _posts = MutableLiveData<Resource<List<BlogPost>>>()
    val postsLD: LiveData<Resource<List<BlogPost>>> get() = _posts
    fun getBlogPosts(){
       // postsLD = splashRepository.getPosts()?.asLiveData()
        viewModelScope.launch {
            splashRepository.getPosts().collect {data->
                _posts.value = data
            }
        }

    }
}