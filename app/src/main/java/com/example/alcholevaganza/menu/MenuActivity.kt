package com.example.alcholevaganza.menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.alcholevaganza.R
import com.example.alcholevaganza.databinding.ActivityMenuBinding


class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    companion object{
        var userNumber =""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root

        val intent = intent
        val message = intent.getStringExtra("userId")
        if (message != null) {
            userNumber = message
        }


        setContentView(view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_menu)as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavView = binding.bottomNav
        bottomNavView.setupWithNavController(navController)

    }




}