package com.darkabhi.covidproject.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.fragment)
        setSupportActionBar(binding.appToolbar)
        appBarConfiguration = AppBarConfiguration.Builder(
            setOf(
                R.id.covidResourcesFragment,
                R.id.homeFragment,
                R.id.newsFragment,
                R.id.infoFragment
            )
        ).setOpenableLayout(binding.drawerLayout).build()
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)
        binding.drawerNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment || destination.id == R.id.infoFragment || destination.id == R.id.newsFragment || destination.id == R.id.covidResourcesFragment)
                supportActionBar?.subtitle = ""
        }

        // Do nothing if same item selected
        binding.navigationView.setOnItemReselectedListener { }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportActionBar?.subtitle = ""
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}
