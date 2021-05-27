package com.setiaki.moviecatalogueexpert.di

import com.setiaki.moviecatalogueexpert.core.domain.irepository.ICatalogueRepository
import com.setiaki.moviecatalogueexpert.core.domain.irepository.IDetailRepository
import com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue.GetFavoritedInteractor
import com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue.GetFavoritedUseCase
import com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue.GetTopRatedInteractor
import com.setiaki.moviecatalogueexpert.core.domain.usecase.catalogue.GetTopRatedUseCase
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.GetDetailInteractor
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.GetDetailUseCase
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.ToggleFavoriteStatusInteractor
import com.setiaki.moviecatalogueexpert.core.domain.usecase.detail.ToggleFavoriteStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideGetTopRatedUseCase(catalogueRepository: ICatalogueRepository): GetTopRatedUseCase {
        return GetTopRatedInteractor(catalogueRepository)
    }

    @Singleton
    @Provides
    fun provideGetFavoritedUseCase(catalogueRepository: ICatalogueRepository): GetFavoritedUseCase {
        return GetFavoritedInteractor(catalogueRepository)
    }

    @Singleton
    @Provides
    fun provideGetDetailUseCase(detailRepository: IDetailRepository): GetDetailUseCase {
        return GetDetailInteractor(detailRepository)
    }

    @Singleton
    @Provides
    fun provideToggleFavoriteStatusUseCase(detailRepository: IDetailRepository): ToggleFavoriteStatusUseCase {
        return ToggleFavoriteStatusInteractor(detailRepository)
    }
}