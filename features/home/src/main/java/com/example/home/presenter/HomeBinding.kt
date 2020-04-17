package com.example.home.presenter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.home.data.HomeSection
import com.example.remote.data.Resource
import com.example.uicomponents.helpers.gone
import com.example.uicomponents.helpers.visible

object HomeBinding {
    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: Resource<List<HomeSection>>?) {
        with(recyclerView.adapter as HomeAdapter) {
            resource?.data?.let { updateList(it.toMutableList()) }
        }
    }




    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T> showWhenLoading(view: View, resource: Resource<T>?) {
        resource?.let {
            if (resource.status == Resource.Status.LOADING){
                view.visible()
            }else{
                view.gone()
            }
        }

    }


}