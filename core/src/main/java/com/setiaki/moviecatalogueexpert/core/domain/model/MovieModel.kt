package com.setiaki.moviecatalogueexpert.core.domain.model


data class MovieModel(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val overview: String,
    val genres: List<GenreModel>?,
    val releaseDate: String,
    val voteAverage: Number,
    val isFavorited: Boolean
)
