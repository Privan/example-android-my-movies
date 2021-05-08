package com.example.mymovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.model.Movie
import com.example.mymovies.model.MovieDbClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MainActivity", "onCreate")
        val moviesAdapter = MoviesAdapter(emptyList()) { movie ->
            navigateTo(movie)
        }
        binding.recycler.adapter = moviesAdapter
        lifecycleScope.launch {
            val popularMovies = MovieDbClient.service.listPopularMovies(BuildConfig.THEMOVIEDB_API_KEY)
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }
}


