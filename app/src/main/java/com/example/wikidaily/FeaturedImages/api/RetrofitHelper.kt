package com.example.wikidaily.FeaturedImages.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitHelper {
    private  val base_url = "https://commons.wikimedia.org/w/"

    fun getRetrofitBuilder() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

}
