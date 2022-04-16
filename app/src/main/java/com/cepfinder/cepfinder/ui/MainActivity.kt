package com.cepfinder.cepfinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cepfinder.cepfinder.R
import com.cepfinder.cepfinder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)
        navController = findNavController(R.id.fragment_container)
        navController?.let { navController ->
            val appBarConfig = AppBarConfiguration(
                setOf(
                    R.id.searchFragment,
                    R.id.savedFragment
                )
            )
            setupActionBarWithNavController(navController, appBarConfig)
            binding.bottomNav.setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container)
        return super.onSupportNavigateUp()
    }
}