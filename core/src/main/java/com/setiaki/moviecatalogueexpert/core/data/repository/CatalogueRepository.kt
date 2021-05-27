package com.setiaki.moviecatalogueexpert.core.data.repository

import androidx.paging.*
import com.setiaki.moviecatalogueexpert.core.data.local.database.AppDatabase
import com.setiaki.moviecatalogueexpert.core.data.paging.MovieRemoteMediator
import com.setiaki.moviecatalogueexpert.core.data.paging.TvShowRemoteMediator
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice.Companion.NETWORK_SIZE
import com.setiaki.moviecatalogueexpert.core.data.util.mapper.model.MovieMapper
import com.setiaki.moviecatalogueexpert.core.data.util.mapper.model.TvShowMapper
import com.setiaki.moviecatalogueexpert.core.domain.irepository.ICatalogueRepository
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CatalogueRepository @Inject constructor(
    private val webservice: TMDBWebservice,
    private val database: AppDatabase
) : ICatalogueRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_SIZE, enablePlaceholders = false),
            remoteMediator = MovieRemoteMediator(webservice, database),
            pagingSourceFactory = { database.movieDao().getTopRatedMovies() }
        ).flow.map { paging ->
            paging.map { entity ->
                MovieMapper.mapEntityToModel(entity)
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedTvShows(): Flow<PagingData<TvShowModel>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_SIZE, enablePlaceholders = false),
            remoteMediator = TvShowRemoteMediator(webservice, database),
            pagingSourceFactory = { database.tvShowDao().getTopRatedTvShows() }
        ).flow.map { paging ->
            paging.map { entity ->
                TvShowMapper.mapEntityToModel(entity)
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getFavoritedMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = 4, enablePlaceholders = false),
            pagingSourceFactory = { database.movieDao().getFavoritedMovies() }
        ).flow.map { paging ->
            paging.map { entity ->
                MovieMapper.mapEntityToModel(entity)
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getFavoritedTvShows(): Flow<PagingData<TvShowModel>> {
        return Pager(
            config = PagingConfig(pageSize = 4, enablePlaceholders = false),
            pagingSourceFactory = { database.tvShowDao().getFavoritedTvShow() }
        ).flow.map { paging ->
            paging.map { entity ->
                TvShowMapper.mapEntityToModel(entity)
            }
        }
    }

}