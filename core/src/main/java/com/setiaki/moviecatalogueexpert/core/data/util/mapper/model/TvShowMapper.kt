package com.setiaki.moviecatalogueexpert.core.data.util.mapper.model

import com.setiaki.moviecatalogueexpert.core.data.local.entity.TvShowEntity
import com.setiaki.moviecatalogueexpert.core.data.local.entity.TvShowWithGenre
import com.setiaki.moviecatalogueexpert.core.data.remote.response.TvShowResponse
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel


object TvShowMapper : ModelMapper<TvShowModel, TvShowResponse, TvShowEntity> {
    override fun mapEntityToModel(entity: TvShowEntity): TvShowModel {
        return TvShowModel(
            id = entity.tvShowid,
            title = entity.title,
            posterPath = entity.posterPath,
            genres = null,
            overview = entity.overview,
            releaseDate = entity.releaseDate,
            voteAverage = entity.voteAverage,
            isFavorited = entity.isFavorited
        )
    }

    override fun mapEntityListToModelList(entityList: List<TvShowEntity>): List<TvShowModel> {
        return entityList.map { mapEntityToModel(it) }
    }

    override fun mapResponseToEntity(response: TvShowResponse): TvShowEntity {
        return TvShowEntity(
            tvShowid = response.id,
            title = response.title,
            posterPath = response.posterPath,
            overview = response.overview,
            releaseDate = response.releaseDate,
            voteAverage = response.voteAverage.toFloat(),
            isFavorited = false
        )
    }

    override fun mapResponseListToEntityList(responseList: List<TvShowResponse>): List<TvShowEntity> {
        return responseList.map { mapResponseToEntity(it) }
    }

    fun mapEntityWithGenreToModel(twg: TvShowWithGenre): TvShowModel {
        val tvShow = twg.tvShow
        val genres = twg.genres
        return TvShowModel(
            id = tvShow.tvShowid,
            title = tvShow.title,
            posterPath = tvShow.posterPath,
            genres = GenreMapper.mapEntityListToModelList(genres),
            overview = tvShow.overview,
            releaseDate = tvShow.releaseDate,
            voteAverage = tvShow.voteAverage,
            isFavorited = tvShow.isFavorited
        )
    }

}