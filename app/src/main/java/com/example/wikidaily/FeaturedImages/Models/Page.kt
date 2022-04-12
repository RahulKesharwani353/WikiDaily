package com.example.wikidaily.FeaturedImages.Models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey



data class Page (
                 var pageid: Int? = null,
                 var ns: Int? = null,
                 var title: String? = null,
                 var imagerepository: String? = null,
                 var imageinfo: ImageinfoItem)