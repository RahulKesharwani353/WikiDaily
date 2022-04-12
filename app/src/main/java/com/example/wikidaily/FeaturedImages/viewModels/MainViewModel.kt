package com.example.wikidaily.FeaturedImages.viewModels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikidaily.FeaturedImages.Models.FeaturedImagesList
import com.example.wikidaily.FeaturedImages.repo.FeaturedImagesRepo
import kotlinx.coroutines.launch

class MainViewModel(private val repo: FeaturedImagesRepo): ViewModel() {

//    init {
//        viewModelScope.launch {
//
//        }
//    }
    suspend fun getFeaturedImage(){
    Log.d("REQUEST", "FIRST")
    repo.getFeaturedImages("query","imageinfo","timestamp%7Cuser%7Curl"
        ,"categorymembers", "file", "Category:Featured_pictures_on_Wikimedia_Commons","json"
    )
    }

    suspend fun continueLoadingImages( continuee : String){
        Log.d("REQUEST", "Continuee")
        repo.continueLoadingImages(continuee)
    }

     val featuredImages : LiveData<FeaturedImagesList>
     get() = repo.featuredImages
    var imageListSize: Int = 0
    var isimageListchanged : LiveData<Boolean> = repo.isimageListchanged
}
