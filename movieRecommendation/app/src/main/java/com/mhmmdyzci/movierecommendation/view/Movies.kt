package com.mhmmdyzci.movierecommendation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mhmmdyzci.movierecommendation.databinding.ActivityMainBinding
import com.mhmmdyzci.movierecommendation.databinding.ActivityMoviesBinding

class Movies : AppCompatActivity() {
    lateinit var binding: ActivityMoviesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


}