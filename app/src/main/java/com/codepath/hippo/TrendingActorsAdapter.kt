package com.codepath.hippo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.hippo.R

const val TRENDING_ACTORS_EXTRA = "TRENDING_ACTORS_EXTRA"
private const val TAG = "ArticleAdapter"

class TrendingActorsAdapter(private val context: Context, private val trendingActors: List<TrendingActors>) :
    RecyclerView.Adapter<TrendingActorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }





    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val trendingActor = trendingActors[position]
        holder.bind(trendingActor)
    }

    override fun getItemCount() = trendingActors.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val actorImage = itemView.findViewById<ImageView>(R.id.movieImage)
        private val actorName = itemView.findViewById<TextView>(R.id.actorName)

        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(trendingActors: TrendingActors) {
            actorName.text = trendingActors.name

            Glide.with(context)
                .load(trendingActors.selfieImageUrl)
                .into(actorImage)
        }



        override fun onClick(v: View?) {
            // Get selected article
            val trendingActor = trendingActors[absoluteAdapterPosition]

            //  Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(TRENDING_ACTORS_EXTRA, trendingActor)
            context.startActivity(intent)
        }


    }
}