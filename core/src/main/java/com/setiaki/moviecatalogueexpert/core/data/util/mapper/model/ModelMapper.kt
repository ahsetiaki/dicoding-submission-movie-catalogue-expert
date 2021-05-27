package com.setiaki.moviecatalogueexpert.core.data.util.mapper.model


interface ModelMapper<Model, Response, Entity> {
    fun mapEntityToModel(entity: Entity) : Model
    fun mapEntityListToModelList(entityList : List<Entity>) : List<Model>
    fun mapResponseToEntity(response: Response) : Entity
    fun mapResponseListToEntityList(responseList: List<Response>) : List<Entity>
}