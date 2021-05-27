package com.setiaki.moviecatalogueexpert.favorite.presentation.catalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue.GetFavoritedUseCase
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
class FavoriteCatalogueViewModel @Inject constructor(
    private val getFavoritedUseCase: GetFavoritedUseCase
) : ViewModel() {

    fun getFavoritedMovies(): LiveData<PagingData<MovieModel>> {
        return getFavoritedUseCase.getFavoritedMovies().asLiveData().cachedIn(viewModelScope)
    }

    fun getFavoritedTvShows(): LiveData<PagingData<TvShowModel>> {
        return getFavoritedUseCase.getFavoritedTvShows().asLiveData().cachedIn(viewModelScope)
    }

}