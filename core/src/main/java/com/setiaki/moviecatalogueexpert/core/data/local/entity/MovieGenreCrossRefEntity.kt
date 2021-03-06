package com.setiaki.moviecatalogueexpert.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "movie_genre_cross_ref", primaryKeys = ["movie_id", "genre_id"])
data class MovieGenreCrossRefEntity(
    @ColumnInfo(name = "movie_id", index = true)
    @NonNull
    val movieId: Int,

    @ColumnInfo(name = "genre_id", index = true)
    @NonNull
    val genreId: Int
)
