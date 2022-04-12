package com.example.wikidaily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import com.example.wikidaily.FeaturedImages.api.FeaturedImagesServices
import com.example.wikidaily.FeaturedImages.api.RetrofitHelper
import com.example.wikidaily.FeaturedImages.repo.FeaturedImagesRepo
import com.example.wikidaily.FeaturedImages.viewModels.MainViewModel
import com.example.wikidaily.FeaturedImages.viewModels.ViewModelFactory
import com.example.wikidaily.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.pager.adapter = PagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.pager)
        setContentView(binding.root)}
    }