package com.setiaki.moviecatalogueexpert.di

import com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue.GetFavoritedUseCase
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.GetDetailUseCase
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.ToggleFavoriteStatusUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun exposeGetFavoritedUseCase(): GetFavoritedUseCase

    fun exposeGetDetailUseCase(): GetDetailUseCase

    fun exposeToggleFavoriteStatusUseCase(): ToggleFavoriteStatusUseCase
}