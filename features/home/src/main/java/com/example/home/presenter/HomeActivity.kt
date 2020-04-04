package com.example.home.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.home.R
import com.example.home.data.HomeSection
import com.example.remote.data.Resource
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by viewModel()
    lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initObservables()
    }



    private fun initObservables() {
        viewModel.homeSectionsLD.observe(this, Observer {data->
            data?.let {
                initRecyclerView(data)
            }

        })

        viewModel.gethome()
    }

    private fun initRecyclerView(data: Resource<List<HomeSection>>) {
        data.data?.let {
            homeAdapter = HomeAdapter(it)
            sectionsRv?.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter =homeAdapter
            }
        }

    }
}
