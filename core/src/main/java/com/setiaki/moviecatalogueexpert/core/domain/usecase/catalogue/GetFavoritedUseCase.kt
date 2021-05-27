package com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue

import androidx.paging.PagingData
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow


interface GetFavoritedUseCase {
    fun getFavoritedMovies(): Flow<PagingData<MovieModel>>
    fun getFavoritedTvShows(): Flow<PagingData<TvShowModel>>
}