package com.example.wikidaily.FeaturedImages.Models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "images")
data class ImageinfoItem( @PrimaryKey(autoGenerate = true)
                        var imageId : Int,
                        var timestamp: String? = null,
                          var user: String? = null,
                          var url: String? = null,
                          var descriptionurl: String? = null,
                          var descriptionshorturl: String? = null)