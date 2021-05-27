package com.setiaki.moviecatalogueexpert.core.data.util.mapper.model

import com.setiaki.moviecatalogueexpert.core.data.local.entity.MovieEntity
import com.setiaki.moviecatalogueexpert.core.data.local.entity.MovieWithGenre
import com.setiaki.moviecatalogueexpert.core.data.remote.response.MovieResponse
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel


object MovieMapper : ModelMapper<MovieModel, MovieResponse, MovieEntity> {
    override fun mapEntityToModel(entity: MovieEntity): MovieModel {
        return MovieModel(
            id = entity.movieId,
            title = entity.title,
            posterPath = entity.posterPath,
            genres = null,
            overview = entity.overview,
            releaseDate = entity.releaseDate,
            voteAverage = entity.voteAverage,
            isFavorited = entity.isFavorited
        )
    }

    override fun mapEntityListToModelList(entityList: List<MovieEntity>): List<MovieModel> {
        return entityList.map { mapEntityToModel(it) }
    }

    override fun mapResponseToEntity(response: MovieResponse): MovieEntity {
        return MovieEntity(
            movieId = response.id,
            title = response.title,
            posterPath = response.posterPath,
            overview = response.overview,
            releaseDate = response.releaseDate,
            voteAverage = response.voteAverage.toFloat(),
            isFavorited = false
        )
    }

    override fun mapResponseListToEntityList(responseList: List<MovieResponse>): List<MovieEntity> {
        return responseList.map { mapResponseToEntity(it) }
    }


    fun mapEntityWithGenreToModel(mwg: MovieWithGenre) : MovieModel {
        val movie = mwg.movie
        val genres = mwg.genres
        return MovieModel(
            id = movie.movieId,
            title = movie.title,
            posterPath = movie.posterPath,
            genres = GenreMapper.mapEntityListToModelList(genres),
            overview = movie.overview,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
            isFavorited = movie.isFavorited
        )
    }
}