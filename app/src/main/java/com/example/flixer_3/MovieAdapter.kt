package com.example.flixer_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieName: TextView = itemView.findViewById(R.id.movieName)
        val movieDesc: TextView = itemView.findViewById(R.id.movieDesc)
        val moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movielayout, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val movie = movies[position]

        holder.movieName.text = movie.title
        holder.movieDesc.text = movie.overview

        val imageUrl = "https://image.tmdb.org/t/p/w500" + movie.posterPath

        Glide.with(holder.itemView)
            .load(imageUrl)
            .into(holder.moviePoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}