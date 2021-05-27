package com.setiaki.moviecatalogueexpert.core.data.util.mapper.genrecrossref

import com.setiaki.moviecatalogueexpert.core.data.local.entity.MovieGenreCrossRefEntity
import com.setiaki.moviecatalogueexpert.core.data.remote.response.MovieResponse


object MovieGenreCrossRefMapper : GenreCrossRefMapper<MovieResponse, MovieGenreCrossRefEntity> {
    override fun mapResponseToCrossRefs(response: MovieResponse): List<MovieGenreCrossRefEntity> {
        val crossRefList = mutableListOf<MovieGenreCrossRefEntity>()
        response.genres.forEach { genre ->
            val crossRef = MovieGenreCrossRefEntity(
                movieId = response.id,
                genreId = genre.id
            )
            crossRefList.add(crossRef)
        }
        return crossRefList
    }
}

