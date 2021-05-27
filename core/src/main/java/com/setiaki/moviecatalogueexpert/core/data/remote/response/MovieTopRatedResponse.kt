package com.setiaki.moviecatalogueexpert.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieTopRatedResponse(
    @field:SerializedName("results")
    val results: List<MovieResponse> = arrayListOf()
)
