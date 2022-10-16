package com.codepath.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.codepath.bitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding



    private fun replaceFragment(foodFragment: FoodFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout, foodFragment)
        fragmentTransaction.commit()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val foodFragment: Fragment = FoodFragment()
        val summaryFragment: Fragment = SummaryFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_home -> fragment = foodFragment
                R.id.nav_summary -> fragment = summaryFragment
            }
            fragmentManager.beginTransaction().replace(R.id.food_frame_layout, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_home

        // Call helper method to swap the FrameLayout with the fragment
        replaceFragment(FoodFragment())







        findViewById<Button>(R.id.entry_button).setOnClickListener {
            // TODO: explicit intent to get to "DetailActivity"
            intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)

        }
    }
}