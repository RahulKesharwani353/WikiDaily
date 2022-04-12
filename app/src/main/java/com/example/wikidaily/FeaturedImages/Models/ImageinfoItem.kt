package com.example.wikidaily.FeaturedImages.Models

import androidx.room.Entity
import androidx.room.PrimaryKey



data class ImageinfoItem(
                        var timestamp: String? = null,
                          var user: String? = null,
                          var url: String? = null,
                          var descriptionurl: String? = null,
                          var descriptionshorturl: String? = null)