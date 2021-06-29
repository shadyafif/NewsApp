package com.example.newsapp.Ui.Activities


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.Ui.Fragments.FavoriteFragment
import com.example.newsapp.Ui.Fragments.HomeFragment
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val homeFragment = HomeFragment()
        val favouriteFragment = FavoriteFragment()
        createFragment(homeFragment)

        // Click listener on bottom navigation
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> createFragment(homeFragment)
                R.id.ic_favourite -> createFragment(favouriteFragment)

            }
            true
        }



    }


    private fun createFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }


}