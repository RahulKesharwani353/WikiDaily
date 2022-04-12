package com.example.wikidaily.randomArticle.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wikidaily.randomArticle.models.RandomArticeList
import com.example.wikidaily.randomArticle.models.RandomPageModel
import com.example.wikidaily.randomArticle.repo.RandomArticleRepo
import java.text.FieldPosition

class ArticleMainViewModel(private var  repo: RandomArticleRepo) : ViewModel() {

    suspend fun getRandomArticle()
    {
        repo.getRandomArticle()
    }
    suspend fun getRandArticleDetails(titles:String, position: Int)
    {
        repo.getRandomPageDescriptions(titles,position)
    }
    suspend fun loadRandomPagesContinued()
    {
        repo.loadRandomPagesContinued()
    }



    val randomArticles : LiveData<RandomPageModel>
        get() = repo.randomArticle
    var desc = repo.desc

}