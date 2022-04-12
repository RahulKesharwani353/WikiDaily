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

    @GET("api.php?action=query&prop=imageinfo&iiprop=timestamp%7Cuser%7Curl&generator=categorymembers&gcmtype=file&gcmtitle=Category:Featured_pictures_on_Wikimedia_Commons&format=json&utf8")
    suspend fun getFeaturedImages(
    ) : String

    @GET("api.php?action=query&prop=imageinfo&iiprop=timestamp|user|url&generator=categorymembers&gcmtype=file&gcmtitle=Category:Featured_pictures_on_Wikimedia_Commons&format=json&utf8")
    suspend fun getFeatureContinueImages(@Query("gcmcontinue") continueStr: String): String


}