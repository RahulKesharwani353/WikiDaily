package com.example.wikidaily.randomArticle.models

import androidx.lifecycle.MutableLiveData


data class RandomArticeList(
                            var continuee: Continue? = Continue(),
                            var query: Query? = Query()
)
//data class RandomPage(
//    var pageid: String? = null,
//    var title: String? = null,
//    val content: MutableLiveData<String?> = MutableLiveData("null")
//)