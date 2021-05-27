package com.setiaki.moviecatalogueexpert.core.data.local.dao

import androidx.room.*
import com.setiaki.moviecatalogueexpert.core.data.local.entity.TvShowGenreCrossRefEntity
import com.setiaki.moviecatalogueexpert.core.data.local.entity.TvShowWithGenre
import kotlinx.coroutines.flow.Flow


@Dao
interface TvShowGenreCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTvShowGenreCrossRefs(crossRefs: List<TvShowGenreCrossRefEntity>)

    @Transaction
    @Query("SELECT * FROM tv_shows WHERE tv_show_id = :tvShowId ")
    fun getTvShowWithGenre(tvShowId: Int): Flow<TvShowWithGenre>

    @Query("DELETE FROM tv_show_genre_cross_ref")
    suspend fun clearTvShowGenreCrossRef()
}