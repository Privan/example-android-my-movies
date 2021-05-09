package com.example.mymovies

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.example.mymovies.databinding.ActivityDetailBinding
import com.example.mymovies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null) {
            title = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.poster_path}")
                .into(binding.ivBackdrop)
            binding.tvSummary.text = movie.overview
            bindDetailInfo(binding.tvDetailInfo, movie)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindDetailInfo(tvDetailInfo: AppCompatTextView, movie: Movie) {
        tvDetailInfo.text = buildSpannedString {
            bold { append("Original Language: ") }
            appendLine(movie.original_language)
            bold { append("Original Title: ") }
            appendLine(movie.original_title)
            bold { append("Release Date: ") }
            appendLine(movie.release_date)
            bold { append("Popularity: ") }
            appendLine(movie.popularity.toString())
            bold { append("Vote Average: ") }
            appendLine(movie.vote_average.toString())
        }

    }
}