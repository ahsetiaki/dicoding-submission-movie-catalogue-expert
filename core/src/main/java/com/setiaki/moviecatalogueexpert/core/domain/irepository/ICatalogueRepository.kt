package com.setiaki.moviecatalogueexpert.core.domain.irepository

import androidx.paging.PagingData
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow


interface ICatalogueRepository {
    fun getTopRatedMovies(): Flow<PagingData<MovieModel>>
    fun getTopRatedTvShows(): Flow<PagingData<TvShowModel>>
    fun getFavoritedMovies(): Flow<PagingData<MovieModel>>
    fun getFavoritedTvShows(): Flow<PagingData<TvShowModel>>
}