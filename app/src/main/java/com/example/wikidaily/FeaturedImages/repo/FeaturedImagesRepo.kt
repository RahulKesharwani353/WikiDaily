package com.example.wikidaily.FeaturedImages.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wikidaily.FeaturedImages.Models.FeaturedImagesList
import com.example.wikidaily.FeaturedImages.api.FeaturedImagesServices
import retrofit2.http.Query

class FeaturedImagesRepo(private val featuredImageService: FeaturedImagesServices) {

    private val featuredImageLiveData = MutableLiveData<FeaturedImagesList>()
    val featuredImages : LiveData<FeaturedImagesList>
    get() = featuredImageLiveData
    suspend fun getFeaturedImages(
        action: String,
        prop: String,
        iiprop: String,
        generator: String,
        gcmtype: String,
        gcmtitle: String,
        format: String
    ) {
        val result = featuredImageService.getFeaturedImages(action, prop, iiprop, generator, gcmtype, gcmtitle, format)
        if (result.body() !=null){
            Log.d("JsonAya", result.body().toString())
            featuredImageLiveData.postValue(result.body())
        }
//        if (result.body() !=null){
//            Log.d("JsonAya", result.body().toString())
//            featuredImageLiveData.postValue(result.body())
//        }
    }
}