package com.example.wikidaily.FeaturedImages.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wikidaily.FeaturedImages.Models.Continue
import com.example.wikidaily.FeaturedImages.Models.FeaturedImagesList
import com.example.wikidaily.FeaturedImages.Models.ImageinfoItem
import com.example.wikidaily.FeaturedImages.Models.Page
import com.example.wikidaily.FeaturedImages.api.FeaturedImagesServices
import org.json.JSONObject
import retrofit2.http.Query

class FeaturedImagesRepo(private val featuredImageService: FeaturedImagesServices) {

    var imageList: FeaturedImagesList? = FeaturedImagesList()

    private val featuredImageLiveData = MutableLiveData<FeaturedImagesList>()
    var isimageListchanged: MutableLiveData<Boolean> = MutableLiveData(false)
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
        val result = featuredImageService.getFeaturedImages()
        parseImages(result)

    }


    suspend fun continueLoadingImages(continuee : String) {
        val result = featuredImageService.getFeatureContinueImages(continuee)
        parseImages(result)
        // isContinuedImagesLoaded = true

    }

    fun parseImages(jsonAsString: String) {
        val jsonObj: JSONObject = JSONObject(jsonAsString)


        imageList?.batchcomplete = jsonObj.getString("batchcomplete")
        Log.d("imagelistbachcomplete", "${imageList?.batchcomplete}")
        imageList?.continuee?.gcmcontinue =
            jsonObj.getJSONObject("continue").getString("gcmcontinue")
        imageList?.continuee?.Continue =
            jsonObj.getJSONObject("continue").getString("continue")
        val pagesJsonObj = jsonObj.getJSONObject("query").getJSONObject("pages")
        // imageList?.query?.pages?.pageList = arrayListOf()
        for (pageKey in pagesJsonObj.keys()) {
            val page = pagesJsonObj.getJSONObject(pageKey)
            val imageinfo = page.getJSONArray("imageinfo")
            val pageObj = Page(
                page.getString("pageid").toInt(),
                page.getString("ns").toInt(),
                page.getString("title"),
                page.getString("imagerepository"),
                ImageinfoItem(url = imageinfo.getJSONObject(0).getString("url"))
            )
            Log.d("JasonDataAya", pageObj.toString())
            imageList?.query?.pages?.pageList?.add(pageObj)
            isimageListchanged.value = true


        }
        featuredImageLiveData.postValue(imageList)
    }
}