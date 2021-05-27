package com.setiaki.moviecatalogueexpert.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.setiaki.moviecatalogueexpert.core.data.util.networkbound.Resource
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.GetDetailUseCase
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.ToggleFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopRatedDetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
    private val toggleFavoriteStatusUseCase: ToggleFavoriteStatusUseCase
) : ViewModel() {

    fun getMovieDetail(itemId: Int): LiveData<Resource<MovieModel>> {
        return getDetailUseCase.getMovieDetail(itemId).asLiveData()
    }

    fun getTvShowDetail(itemId: Int): LiveData<Resource<TvShowModel>> {
        return getDetailUseCase.getTvShowDetail(itemId).asLiveData()
    }

    fun toggleMovieFavoriteStatus(itemId: Int) {
        viewModelScope.launch {
            toggleFavoriteStatusUseCase.toggleMovieDetailFavoriteStatus(itemId)
        }

    }

    fun toggleTvShowFavoriteStatus(itemId: Int) {
        viewModelScope.launch {
            toggleFavoriteStatusUseCase.toggleTvShowDetailFavoriteStatus(itemId)
        }
    }

}