package com.example.wikidaily.randomArticle.models

data class Page (var ns: Int = 0,
            var revisions: Revisions,
            var pageid: Int = 0,
            var  title: String = "")