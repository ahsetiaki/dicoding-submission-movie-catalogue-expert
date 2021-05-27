package com.setiaki.moviecatalogueexpert.core.domain.irepository

import com.setiaki.moviecatalogueexpert.core.data.util.networkbound.Resource
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow


interface IDetailRepository {
    fun getMovieDetail(id: Int) : Flow<Resource<MovieModel>>
    fun getTvShowDetail(id: Int) : Flow<Resource<TvShowModel>>
    suspend fun toggleMovieDetailFavoriteStatus(id: Int)
    suspend fun toggleTvShowDetailFavoriteStatus(id: Int)
}