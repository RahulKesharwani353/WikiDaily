package com.example.wikidaily.FeaturedImages.Models

data class Page (var pageid: Int? = null,
                 var ns: Int? = null,
                 var title: String? = null,
                 var imagerepository: String? = null,
                 var imageinfo: ImageinfoItem? = null)