package com.example.wikidaily

import android.app.Application

import com.example.wikidaily.FeaturedImages.api.FeaturedImagesServices
import com.example.wikidaily.FeaturedImages.api.RetrofitHelper
import com.example.wikidaily.FeaturedImages.repo.FeaturedImagesRepo
import com.example.wikidaily.randomArticle.api.RandomArticleServices
import com.example.wikidaily.randomArticle.repo.RandomArticleRepo

class WikiAppilcation: Application() {
    lateinit var featuredImagesRepo: FeaturedImagesRepo
    lateinit var randomArticleRepo: RandomArticleRepo
    override fun onCreate() {
        super.onCreate()
        initilizer()
    }

    private fun initilizer() {
        val featuredImagesServices = RetrofitHelper.getRetrofitBuilder().create(FeaturedImagesServices::class.java)
        featuredImagesRepo = FeaturedImagesRepo(featuredImagesServices,
            //ImagesDatabase.getDatabase(applicationContext),
      applicationContext)

        val randomArticleServices = RetrofitHelper.getRetrofitBuilder().create(RandomArticleServices::class.java)
        randomArticleRepo = RandomArticleRepo(randomArticleServices,
            //ImagesDatabase.getDatabase(applicationContext),
            applicationContext)
    }
}