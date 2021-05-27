package com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue

import androidx.paging.PagingData
import com.setiaki.moviecatalogueexpert.core.domain.irepository.ICatalogueRepository
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetFavoritedInteractor @Inject constructor(
    private val catalogueRepository: ICatalogueRepository
) : GetFavoritedUseCase {

    override fun getFavoritedMovies(): Flow<PagingData<MovieModel>> {
        return catalogueRepository.getFavoritedMovies()
    }

    override fun getFavoritedTvShows(): Flow<PagingData<TvShowModel>> {
        return catalogueRepository.getFavoritedTvShows()
    }
}