package com.example.home.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.home.R
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getHomeBtn.setOnClickListener {
            viewModel.gethome()
        }

        initObservables()
    }


    private fun initObservables() {
        viewModel.homeSectionsLD.observe(this, Observer {data->
            data?.let {

            }

        })
    }
}
