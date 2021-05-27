package com.setiaki.moviecatalogueexpert.core.data.remote.response

import com.google.gson.annotations.SerializedName


data class MovieResponse(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("overview")
    val overview: String = "",

    @field:SerializedName("genres")
    val genres: List<GenreResponse> = arrayListOf(),

    @field:SerializedName("release_date")
    val releaseDate: String = "",

    @field:SerializedName("vote_average")
    val voteAverage: Number = 0
)
