package com.example.wikidaily.FeaturedImages.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private  val base_url = "https://commons.wikimedia.org/"

    fun getRetrofitBuilder() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
//
//            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}