package com.example.wikidaily.FeaturedImages.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wikidaily.FeaturedImages.repo.FeaturedImagesRepo

class ViewModelFactory(private val repo: FeaturedImagesRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }


}