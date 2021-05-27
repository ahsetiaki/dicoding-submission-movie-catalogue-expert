package com.setiaki.moviecatalogueexpert.core.data.util.mapper.model

import com.setiaki.moviecatalogueexpert.core.data.local.entity.GenreEntity
import com.setiaki.moviecatalogueexpert.core.data.remote.response.GenreResponse
import com.setiaki.moviecatalogueexpert.core.domain.model.GenreModel


object GenreMapper : ModelMapper<GenreModel, GenreResponse, GenreEntity> {
    override fun mapEntityToModel(entity: GenreEntity): GenreModel {
        return GenreModel(
            id = entity.genreId,
            name = entity.name
        )
    }

    override fun mapEntityListToModelList(entityList: List<GenreEntity>): List<GenreModel> {
        return entityList.map { mapEntityToModel(it) }
    }

    override fun mapResponseToEntity(response: GenreResponse): GenreEntity {
        return GenreEntity(
            genreId = response.id,
            name = response.name
        )
    }

    override fun mapResponseListToEntityList(responseList: List<GenreResponse>): List<GenreEntity> {
        return responseList.map { mapResponseToEntity(it) }
    }


}