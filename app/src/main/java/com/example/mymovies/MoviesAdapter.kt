package com.example.mymovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovies.databinding.ViewMovieItemBinding
import com.example.mymovies.model.Movie

class MoviesAdapter(
    var movies: List<Movie>,
    private val MovieClickedListener: (Movie) -> Unit) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            MovieClickedListener(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(private val binding: ViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                .into(binding.ivCover)
        }
    }
}