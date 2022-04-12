package com.example.wikidaily.randomArticle.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wikidaily.randomArticle.repo.RandomArticleRepo


class ViewModelFactory(private val repo: RandomArticleRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleMainViewModel(repo) as T
    }


}