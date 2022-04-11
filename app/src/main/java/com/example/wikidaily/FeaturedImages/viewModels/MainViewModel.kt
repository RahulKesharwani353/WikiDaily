package com.example.wikidaily.FeaturedImages.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikidaily.FeaturedImages.Models.FeaturedImagesList
import com.example.wikidaily.FeaturedImages.repo.FeaturedImagesRepo
import kotlinx.coroutines.launch

class MainViewModel(private val repo: FeaturedImagesRepo): ViewModel() {

    init {
        viewModelScope.launch {
            repo.getFeaturedImages("query","imageinfo","timestamp%7Cuser%7Curl"
            ,"categorymembers", "file", "Category:Featured_pictures_on_Wikimedia_Commons","json"
            )
        }
    }

     val featuredImages : LiveData<FeaturedImagesList>
     get() = repo.featuredImages
}
