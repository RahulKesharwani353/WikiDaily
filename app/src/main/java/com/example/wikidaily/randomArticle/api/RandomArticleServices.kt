package com.example.wikidaily.randomArticle.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomArticleServices {

    @GET("api.php?format=json&action=query&generator=random&grnnamespace=0&prop=revisions%7Cimages&rvprop=content&grnlimit=10")
    suspend fun getRandomArticle() : String

    @GET("api.php?action=query&prop=extracts&exsentences=4&exlimit=1&explaintext=1&formatversion=2&format=json&utf8")
    suspend fun getRandomPageDescriptions(@Query("titles") title: String): String

    @GET("api.php?action=query&generator=random&grnlimit=10&grnnamespace=0&prop=info&format=json&utf8")
    suspend fun getRandomPageContinued(@Query("grncontinue") continuee: String): String

}