package com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue

import androidx.paging.PagingData
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow


interface GetTopRatedUseCase {
    fun getTopRatedMovies(): Flow<PagingData<MovieModel>>
    fun getTopRatedTvShows(): Flow<PagingData<TvShowModel>>
}