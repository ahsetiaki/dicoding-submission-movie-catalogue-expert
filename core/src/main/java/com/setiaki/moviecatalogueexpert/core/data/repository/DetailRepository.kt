package com.setiaki.moviecatalogueexpert.core.data.repository

import androidx.room.withTransaction
import com.setiaki.moviecatalogueexpert.core.data.local.database.AppDatabase
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice
import com.setiaki.moviecatalogueexpert.core.data.util.mapper.genrecrossref.MovieGenreCrossRefMapper
import com.setiaki.moviecatalogueexpert.core.data.util.mapper.genrecrossref.TvShowGenreCrossRefMapper
import com.setiaki.moviecatalogueexpert.core.data.util.mapper.model.GenreMapper
import com.setiaki.moviecatalogueexpert.core.data.util.mapper.model.MovieMapper
import com.setiaki.moviecatalogueexpert.core.data.util.mapper.model.TvShowMapper
import com.setiaki.moviecatalogueexpert.core.data.util.networkbound.Resource
import com.setiaki.moviecatalogueexpert.core.data.util.networkbound.networkBoundResource
import com.setiaki.moviecatalogueexpert.core.domain.irepository.IDetailRepository
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DetailRepository @Inject constructor(
    private val webservice: TMDBWebservice,
    private val database: AppDatabase
) : IDetailRepository {

    override fun getMovieDetail(id: Int): Flow<Resource<MovieModel>> {
        return networkBoundResource(
            query = {
                database.movieGenreCrossRefDao().getMovieWithGenre(id).map {
                    MovieMapper.mapEntityWithGenreToModel(it)
                }
            },
            fetch = { webservice.getMovieDetail(id) },
            saveFetchResult = {
                val movieResponse = it
                val genreResponses = movieResponse.genres

                val movieEntity = MovieMapper.mapResponseToEntity(movieResponse)
                val genreEntities = GenreMapper.mapResponseListToEntityList(genreResponses)
                val movieGenreCrossRefEntities =
                    MovieGenreCrossRefMapper.mapResponseToCrossRefs(movieResponse)

                with(database) {
                    withTransaction {
                        movieDao().insertMovie(movieEntity)
                        genreDao().insertGenres(genreEntities)
                        movieGenreCrossRefDao().insertMovieGenreCrossRefs(movieGenreCrossRefEntities)
                    }
                }
            },
            shouldFetch = { it.genres.isNullOrEmpty() }
        )
    }

    override fun getTvShowDetail(id: Int): Flow<Resource<TvShowModel>> {
        return networkBoundResource(
            query = {
                database.tvShowGenreCrossRefDao().getTvShowWithGenre(id).map {
                    TvShowMapper.mapEntityWithGenreToModel(it)
                }
            },
            fetch = { webservice.getTvShowDetail(id) },
            saveFetchResult = {
                val tvShowResponse = it
                val genreResponses = tvShowResponse.genres

                val tvShowEntity = TvShowMapper.mapResponseToEntity(tvShowResponse)
                val genreEntities = GenreMapper.mapResponseListToEntityList(genreResponses)
                val tvShowGenreCrossRefEntities =
                    TvShowGenreCrossRefMapper.mapResponseToCrossRefs(tvShowResponse)

                with(database) {
                    withTransaction {
                        tvShowDao().insertTvShow(tvShowEntity)
                        genreDao().insertGenres(genreEntities)
                        tvShowGenreCrossRefDao().insertTvShowGenreCrossRefs(
                            tvShowGenreCrossRefEntities
                        )
                    }
                }
            },
            shouldFetch = { it.genres.isNullOrEmpty() }
        )
    }

    override suspend fun toggleMovieDetailFavoriteStatus(id: Int) {
        with(database) {
            val toggledEntity = movieDao().getMovie(id).apply {
                this.isFavorited = !this.isFavorited
            }
            movieDao().updateMovie(toggledEntity)
        }
    }

    override suspend fun toggleTvShowDetailFavoriteStatus(id: Int) {
        with(database) {
            val toggledEntity = tvShowDao().getTvShow(id).apply {
                this.isFavorited = !this.isFavorited
            }
            tvShowDao().updateTvShow(toggledEntity)
        }
    }

}