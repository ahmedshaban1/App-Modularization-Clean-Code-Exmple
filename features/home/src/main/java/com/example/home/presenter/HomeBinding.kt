package com.example.home.presenter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.home.data.HomeSection
import com.example.remote.data.Resource

object HomeBinding {
    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: Resource<List<HomeSection>>?) {
        with(recyclerView.adapter as HomeAdapter) {
            resource?.data?.let { updateList(it) }
        }
    }


    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: HomeSection?) {
        with(recyclerView.adapter as HomeInnerAdapter) {
            resource?.let { updateSection(it) }
        }
    }




}