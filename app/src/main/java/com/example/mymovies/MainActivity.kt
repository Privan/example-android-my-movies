package com.example.mymovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.model.Movie
import com.example.mymovies.model.MovieDbClient
import com.example.mymovies.model.MovieDbResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var moviesAdapter: MoviesAdapter

    private val movieResult: MutableLiveData<MovieDbResult> by lazy { MutableLiveData<MovieDbResult>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MainActivity", "onCreate")
        moviesAdapter = MoviesAdapter(mutableListOf()) { movie ->
            navigateTo(movie)
        }
        binding.recycler.adapter = moviesAdapter
        movieResult.observe(this, {
            moviesAdapter.addMovies(it.results)
            binding.pbMovie.visibility = View.GONE
        })
        lifecycleScope.launch(context = Dispatchers.IO) {
            val popularMovies = MovieDbClient
                .service
                .listPopularMovies(BuildConfig.THEMOVIEDB_API_KEY)
            movieResult.postValue(popularMovies)
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


