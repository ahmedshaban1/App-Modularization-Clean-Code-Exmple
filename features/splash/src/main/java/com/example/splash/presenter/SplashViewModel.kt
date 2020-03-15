package com.example.splash.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splash.data.BlogPost
import com.example.splash.data.SplashRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SplashViewModel(val splashRepository: SplashRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<BlogPost>>()
    val postsLD: LiveData<List<BlogPost>> get() = _posts
    fun getBlogPosts(){
        viewModelScope.launch {
            splashRepository.getFoo()
                .catch {case ->
                    Log.d("sssss",case.message)
                }
                .collect{items->
                    _posts.value = items
                }
        }
    }
}