package com.setiaki.moviecatalogueexpert.core.domain.usecase.detail

import com.setiaki.moviecatalogueexpert.core.data.util.networkbound.Resource
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow


interface GetDetailUseCase {
    fun getMovieDetail(id: Int): Flow<Resource<MovieModel>>
    fun getTvShowDetail(id: Int): Flow<Resource<TvShowModel>>
}