package com.example.auth.presenter

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.auth.R
import com.example.auth.databinding.ActivityAuthBinding
import com.example.common.BaseActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(instance: Bundle?, binding: ActivityAuthBinding) {
        configureNavController()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun configureNavController() {
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    override val layoutId = R.layout.activity_auth
}
