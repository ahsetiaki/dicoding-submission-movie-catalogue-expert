package com.setiaki.moviecatalogueexpert.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "genre_id")
    val genreId: Int,

    @ColumnInfo(name = "name")
    @NonNull
    val name: String
)
