package com.example.home.presenter

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.common.BaseActivity
import com.example.home.R
import com.example.home.databinding.ActivityHomeBinding
import com.example.remote.data.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    val viewModel: HomeViewModel by viewModel()

    override fun onCreate(instance: Bundle?, binding: ActivityHomeBinding) {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        initObservables()
        initRecyclerView()
    }

    private fun initObservables() {
        viewModel.homeSectionsLD.observe(this, Observer {
            if(it.status == Resource.Status.ERROR){
                it.messageType?.let { it1 -> handleMessages(it1) }
            }
        })

        viewModel.gethome()
    }

    private fun initRecyclerView() {
        getCurrentViewBinding().sectionsRv.adapter = HomeAdapter(viewModel = viewModel)

    }

    override val layoutId = R.layout.activity_home
}
