package com.codepath.hippo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.hippo.R
import com.codepath.hippo.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val TRENDING_ACTORS_SEARCH_URL =
    "https://api.themoviedb.org/3/trending/person/day?api_key=${SEARCH_API_KEY}"

class MainActivity : AppCompatActivity() {
    private lateinit var trendingActorsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val trendingactors = mutableListOf<TrendingActors>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        trendingActorsRecyclerView = findViewById(R.id.trending_actors)
        // TODO: Set up ArticleAdapter with articles
        val trendingActorsAdapter = TrendingActorsAdapter(this, trendingactors)
        trendingActorsRecyclerView.adapter = trendingActorsAdapter



        trendingActorsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            trendingActorsRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client.get(TRENDING_ACTORS_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch trending actors: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched trending actors: $json")
                try {
                    // TODO: Create the parsedJSON

                    // TODO: Do something with the returned json (contains article information)
                    val parsedJson = createJson().decodeFromString(
                        SearchTrendingActorsResults.serializer(),
                        json.jsonObject.toString()
                    )

                    // TODO: Save the articles and reload the screen
                    // Save the articles
                    parsedJson.results?.let { list ->                        trendingactors.addAll(list)

                        // Reload the screen
                        trendingActorsAdapter.notifyDataSetChanged()
                    }

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }
}