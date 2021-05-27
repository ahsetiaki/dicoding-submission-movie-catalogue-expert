package com.setiaki.moviecatalogueexpert.core.domain.usecase.detail

import com.setiaki.moviecatalogueexpert.core.data.util.networkbound.Resource
import com.setiaki.moviecatalogueexpert.core.domain.irepository.IDetailRepository
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetDetailInteractor @Inject constructor(
    private val detailRepository: IDetailRepository
) : GetDetailUseCase {

    override fun getMovieDetail(id: Int): Flow<Resource<MovieModel>> {
        return detailRepository.getMovieDetail(id)
    }

    override fun getTvShowDetail(id: Int): Flow<Resource<TvShowModel>> {
        return detailRepository.getTvShowDetail(id)
    }

}