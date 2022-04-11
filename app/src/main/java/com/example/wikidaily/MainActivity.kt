package com.example.wikidaily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

import androidx.lifecycle.ViewModelProvider
import com.example.wikidaily.FeaturedImages.api.FeaturedImagesServices
import com.example.wikidaily.FeaturedImages.api.RetrofitHelper
import com.example.wikidaily.FeaturedImages.repo.FeaturedImagesRepo
import com.example.wikidaily.FeaturedImages.viewModels.MainViewModel
import com.example.wikidaily.FeaturedImages.viewModels.ViewModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val featuredImagesServices = RetrofitHelper.getRetrofitBuilder().create(FeaturedImagesServices::class.java)
        val repo = FeaturedImagesRepo(featuredImagesServices)

        viewModel = ViewModelProvider(this,ViewModelFactory(repo))[MainViewModel::class.java]
        viewModel.featuredImages.observe(this) {
            Log.d("aya", it.query?.pages.toString())
            val text = findViewById<TextView>(R.id.hey)
            if (it.query?.pages != null)
            text.text = it.query!!.pages.toString()
            else
                text.text = it.query?.pages.toString()
        }
    }
}