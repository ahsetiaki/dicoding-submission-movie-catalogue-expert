package com.setiaki.moviecatalogueexpert.core.data.util.mapper.genrecrossref

interface GenreCrossRefMapper<Response, GenreCrossRefEntity> {
    fun mapResponseToCrossRefs(response: Response): List<GenreCrossRefEntity>
}