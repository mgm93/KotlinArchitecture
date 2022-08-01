package com.mgm.s1_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mgm.s1_navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //binding
    private lateinit var  binding: ActivityMainBinding
    //other
    private lateinit var navController:NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            //init navController
            navController = findNavController(R.id.navHost)
            appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.detailsFragment))
            setupActionBarWithNavController(navController,appBarConfiguration)
        }
    }

    //handle back
    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}