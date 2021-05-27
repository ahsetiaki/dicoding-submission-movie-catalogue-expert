package com.setiaki.moviecatalogueexpert.core.domain.usecase.detail


interface ToggleFavoriteStatusUseCase {
    suspend fun toggleMovieDetailFavoriteStatus(id: Int)
    suspend fun toggleTvShowDetailFavoriteStatus(id: Int)
}