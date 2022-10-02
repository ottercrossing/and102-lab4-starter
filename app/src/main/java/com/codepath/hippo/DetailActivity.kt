package com.codepath.hippo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.hippo.R


private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var posterImageView: ImageView
    private lateinit var actorNameTextView: TextView
    private lateinit var moveTitleTextView: TextView
    private lateinit var movieOverviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        posterImageView = findViewById(R.id.movieImage)
        actorNameTextView = findViewById(R.id.actorName)
        moveTitleTextView = findViewById(R.id.movieTitle)
        movieOverviewTextView = findViewById(R.id.movieOverview)

        // TODO: Get the extra from the Intent
        val trendingActors = intent.getSerializableExtra(TRENDING_ACTORS_EXTRA) as TrendingActors

        // TODO: Set the title, byline, and abstract information from the article
        actorNameTextView.text = trendingActors.name
        moveTitleTextView.text = "Known for " + trendingActors.knownFor?.firstOrNull { it.title != null }?.title
        movieOverviewTextView.text = "Overview of movie:\n" + trendingActors.knownFor?.firstOrNull { it.overview != null }?.overview

        // TODO: Load the media image
        Glide.with(this)
            .load(trendingActors.posterImageUrl)
            .into(posterImageView)

    }
}