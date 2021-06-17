package com.setiaki.moviecatalogueexpert.presentation.catalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue.GetTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TopRatedCatalogueViewModel @Inject constructor(
    private val getTopRatedUseCase: GetTopRatedUseCase
) : ViewModel() {

    fun getTopRatedMovies(): LiveData<PagingData<MovieModel>> {
        return getTopRatedUseCase.getTopRatedMovies().asLiveData().cachedIn(viewModelScope)
    }

    fun getTopRatedTvShows(): LiveData<PagingData<TvShowModel>> {
        return getTopRatedUseCase.getTopRatedTvShows().asLiveData().cachedIn(viewModelScope)
    }

}