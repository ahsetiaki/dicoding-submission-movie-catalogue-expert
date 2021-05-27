package com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue

import androidx.paging.PagingData
import com.setiaki.moviecatalogueexpert.core.domain.irepository.ICatalogueRepository
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTopRatedInteractor @Inject constructor(
    private val catalogueRepository: ICatalogueRepository
) : GetTopRatedUseCase {

    override fun getTopRatedMovies(): Flow<PagingData<MovieModel>> {
        return catalogueRepository.getTopRatedMovies()
    }

    override fun getTopRatedTvShows(): Flow<PagingData<TvShowModel>> {
        return catalogueRepository.getTopRatedTvShows()
    }



}