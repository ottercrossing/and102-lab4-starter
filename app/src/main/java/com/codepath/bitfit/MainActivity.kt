package com.codepath.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bitfit.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val foods = mutableListOf<FoodEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        foodsRecyclerView = findViewById(R.id.recycler_view)
        // TODO: Set up ArticleAdapter with articles
        val foodAdapter = FoodAdapter(this, foods)
        foodsRecyclerView.adapter = foodAdapter


        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(
                        entity.food,
                        entity.calories
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        //Decorative divider line between items in recyclerview
        foodsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodsRecyclerView.addItemDecoration(dividerItemDecoration)
        }


        findViewById<Button>(R.id.entry_button).setOnClickListener {
            // TODO: explicit intent to get to "DetailActivity"
            intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)

        }
    }
}