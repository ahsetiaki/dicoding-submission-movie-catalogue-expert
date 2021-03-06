package com.setiaki.moviecatalogueexpert.core.data.local.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tv_shows")
data class TvShowEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "tv_show_id")
    val tvShowid: Int,

    @ColumnInfo(name = "title")
    @NonNull
    val title: String,

    @ColumnInfo(name = "poster_path")
    @Nullable
    val posterPath: String?,

    @ColumnInfo(name = "overview")
    @NonNull
    val overview: String,

    @ColumnInfo(name = "release_date")
    @NonNull
    val releaseDate: String,

    @ColumnInfo(name = "vote_average")
    @NonNull
    val voteAverage: Float,

    @ColumnInfo(name = "is_favorited", defaultValue = "0")
    @NonNull
    var isFavorited: Boolean
)
