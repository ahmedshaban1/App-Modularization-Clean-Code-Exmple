package com.example.home.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.data.HomeSection
import com.example.home.domain.GetHomeUserCase
import com.example.remote.data.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val userCase: GetHomeUserCase) : ViewModel() {
    private val _homeSections = MutableLiveData<Resource<List<HomeSection>>>()
    val homeSectionsLD: LiveData<Resource<List<HomeSection>>> get() = _homeSections
    fun gethome() {
        viewModelScope.launch {
            userCase.invoke().collect { data ->
                _homeSections.value = data
            }
        }

    }
}