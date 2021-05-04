package com.example.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mymovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MainActivity", "onCreate")
        binding.recycler.adapter = MoviesAdapter(
                listOf(
                        Movie("Title 1", "https://loremflickr.com/320/240?lock=1"),
                        Movie("Title 2", "https://loremflickr.com/320/240?lock=2"),
                        Movie("Title 3", "https://loremflickr.com/320/240?lock=3"),
                        Movie("Title 4", "https://loremflickr.com/320/240?lock=4")
                )
        ) { movie ->
            Toast
                    .makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT)
                    .show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }
}


