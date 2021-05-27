package com.setiaki.moviecatalogueexpert.core.domain.usecase.detail

import com.setiaki.moviecatalogueexpert.core.domain.irepository.IDetailRepository
import javax.inject.Inject


class ToggleFavoriteStatusInteractor @Inject constructor(
    private val detailRepository: IDetailRepository
) : ToggleFavoriteStatusUseCase {

    override suspend fun toggleMovieDetailFavoriteStatus(id: Int) {
        detailRepository.toggleMovieDetailFavoriteStatus(id)
    }

    override suspend fun toggleTvShowDetailFavoriteStatus(id: Int) {
        detailRepository.toggleTvShowDetailFavoriteStatus(id)
    }
}