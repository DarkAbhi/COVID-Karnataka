package com.darkabhi.covidproject.ui.activity

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.darkabhi.covidproject.R
import com.darkabhi.covidproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.readFromDataStore.observe(this) {
            if (it)
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                )
            else
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                )
        }
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
            if (destination.id == R.id.homeFragment || destination.id ==R.id.infoFragment || destination.id ==R.id.newsFragment ||destination.id ==R.id.covidResourcesFragment)
                supportActionBar?.subtitle = ""
        }

        // Do nothing if same item selected
        binding.navigationView.setOnNavigationItemReselectedListener { }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportActionBar?.subtitle = ""
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}