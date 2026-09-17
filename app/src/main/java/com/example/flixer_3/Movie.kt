package com.example.flixer_3

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null
}