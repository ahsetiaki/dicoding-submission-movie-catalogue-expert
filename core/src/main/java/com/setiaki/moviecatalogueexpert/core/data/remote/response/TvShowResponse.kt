package com.setiaki.moviecatalogueexpert.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val title: String = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("overview")
    val overview: String = "",

    @field:SerializedName("genres")
    val genres: List<GenreResponse> = arrayListOf(),

    @field:SerializedName("first_air_date")
    val releaseDate: String = "",

    @field:SerializedName("vote_average")
    val voteAverage: Number = 0
)
