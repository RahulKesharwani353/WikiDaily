package com.example.wikidaily.FeaturedImages.api

import com.example.wikidaily.FeaturedImages.Models.FeaturedImagesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeaturedImagesServices {
//    @GET("/w/api.php")
//    suspend fun getFeaturedImages(@Query("action") action: String,
//                                  @Query("prop") prop: String,
//                                  @Query("iiprop") iiprop: String,
//                                  @Query("generator") generator: String,
//                                  @Query("gcmtype") gcmtype: String,
//                                  @Query("gcmtitle") gcmtitle: String,
//                                  @Query("format") format: String
//
//    ) : Response<FeaturedImagesList>

    @GET("/w/api.php")
    suspend fun getFeaturedImages(@Query("action") action: String,
                                  @Query("prop") prop: String,
                                  @Query("iiprop") iiprop: String,
                                  @Query("generator") generator: String,
                                  @Query("gcmtype") gcmtype: String,
                                  @Query("gcmtitle") gcmtitle: String,
                                  @Query("format") format: String

    ) : Response<FeaturedImagesList>

}