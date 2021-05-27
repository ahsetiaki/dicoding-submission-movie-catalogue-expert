package com.setiaki.moviecatalogueexpert.core.data.util.mapper.genrecrossref

import com.setiaki.moviecatalogueexpert.core.data.local.entity.TvShowGenreCrossRefEntity
import com.setiaki.moviecatalogueexpert.core.data.remote.response.TvShowResponse


object TvShowGenreCrossRefMapper : GenreCrossRefMapper<TvShowResponse, TvShowGenreCrossRefEntity> {
    override fun mapResponseToCrossRefs(response: TvShowResponse): List<TvShowGenreCrossRefEntity> {
        val crossRefList = mutableListOf<TvShowGenreCrossRefEntity>()
        response.genres.forEach { genre ->
            val crossRef = TvShowGenreCrossRefEntity(
                tvShowId = response.id,
                genreId = genre.id
            )
            crossRefList.add(crossRef)
        }
        return crossRefList
    }


}