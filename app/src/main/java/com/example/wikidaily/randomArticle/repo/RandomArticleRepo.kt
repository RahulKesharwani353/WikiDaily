package com.example.wikidaily.randomArticle.repo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsify.utlis.NetworksUtil
import com.example.wikidaily.randomArticle.api.RandomArticleServices
import com.example.wikidaily.randomArticle.models.*
import org.json.JSONObject

class RandomArticleRepo (private val randomArticleServices: RandomArticleServices,
    //private val imagesDatabase: ImagesDatabase,
                         private val context: Context
) {

    var articleList: RandomArticeList? = RandomArticeList()
    private var randomPages: RandomPageModel = RandomPageModel()
    private val randomArticleData = MutableLiveData<RandomPageModel>()
    var desc: MutableLiveData<String> =  MutableLiveData("")
    val articleDsc :LiveData<String>
        get() = desc
    var isimageListchanged: MutableLiveData<Boolean> = MutableLiveData(false)
    val randomArticle: LiveData<RandomPageModel>
        get() = randomArticleData


    suspend fun getRandomArticle() {
        if (NetworksUtil.isOnline(context)) {
            val result = randomArticleServices.getRandomArticle()
            parseArticle(result)
        } else {
            Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
//            val images  = imagesDatabase.newsDatabase().getImage()
//            val newImgList = Pages(images as ArrayList<Page?>)
//            featuredImageLiveData.postValue(imageList)
        }
    }
    suspend fun getRandomPageDescriptions(titles: String, position:Int) {
        if (NetworksUtil.isOnline(context)) {
            val jsonAsString = randomArticleServices.getRandomPageDescriptions(titles)
            val json = JSONObject(jsonAsString)
            randomPages.pages.get(position).content.value  =
                json.getJSONObject("query").getJSONArray("pages").getJSONObject(0).getString("extract")
            desc.value =  json.getJSONObject("query").getJSONArray("pages").getJSONObject(0).getString("extract")
        } else {
            Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
//            val images  = imagesDatabase.newsDatabase().getImage()
//            val newImgList = Pages(images as ArrayList<Page?>)
//            featuredImageLiveData.postValue(imageList)
        }
    }


    suspend fun loadRandomPagesContinued() {
        val jsonAsString: String =
            randomArticleServices.getRandomPageContinued(randomPages.continuee!!)
        parseArticle(jsonAsString)
    }
    suspend fun parseArticle(jsonAsString: String) {
//        val jsonObj: JSONObject = JSONObject(jsonAsString)
//
//        articleList?.continuee?.grncontinue =
//            jsonObj.getJSONObject("continue").getString("grncontinue")
//        articleList?.continuee?.continuee =
//            jsonObj.getJSONObject("continue").getString("continue")
//        val pagesJsonObj = jsonObj.getJSONObject("query").getJSONObject("pages")
//        // imageList?.query?.pages?.pageList = arrayListOf()
//        for (pageKey in pagesJsonObj.keys()) {
//            val page = pagesJsonObj.getJSONObject(pageKey)
//            val revisionArray = page.getJSONArray("revisions")
//            val pageObj = Page(
//                page.getString("ns").toInt(),
//                Revisions(desc = revisionArray.getJSONObject(0).getString("*")),
//                page.getString("pageid").toInt(),
//                page.getString("title")
//            )
//            Log.d("JasonDataAya", pageObj.toString())
//            articleList?.query?.pages?.pageList?.add(pageObj)
//            isimageListchanged.value = true
        val jsonObj = JSONObject(jsonAsString)
        randomPages.continuee = jsonObj.getJSONObject("continue").getString("grncontinue")
        val pages: JSONObject = jsonObj.getJSONObject("query").getJSONObject("pages")
        for (pageKey in pages.keys()) {
            val page = pages.getJSONObject(pageKey)
            randomPages.pages.add(
                RandomPage(
                    page.getString("pageid"),
                    page.getString("title"), MutableLiveData("null")
                )
            )
        }
        randomArticleData.postValue(randomPages)
        Log.d("randomPages", randomPages.toString())

        }
//        imageList?.query?.pages?.let { imagesDatabase.newsDatabase().addImage(it.pageList) }


}