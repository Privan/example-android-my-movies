package com.example.mymovies.ui.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.example.mymovies.R
import com.example.mymovies.databinding.ActivityDetailBinding
import com.example.mymovies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null) {
            title = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.poster_path}")
                .into(binding.ivBackdrop)
            binding.tvSummary.text = movie.overview
            bindDetailInfo(movie)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindDetailInfo(movie: Movie) {
        binding.tvDetailInfo.text = buildSpannedString {
            appendInfo(R.string.original_language, movie.original_language)
            appendInfo(R.string.original_title, movie.original_title)
            appendInfo(R.string.release_date, movie.release_date)
            appendInfo(R.string.popularity, movie.popularity.toString())
            appendInfo(R.string.vote_average, movie.vote_average.toString())
        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String ) {
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }
}