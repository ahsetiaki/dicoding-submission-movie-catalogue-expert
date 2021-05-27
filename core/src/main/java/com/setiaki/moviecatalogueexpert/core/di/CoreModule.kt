package com.setiaki.moviecatalogueexpert.core.di

import android.content.Context
import com.setiaki.moviecatalogueexpert.core.data.local.database.AppDatabase
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice
import com.setiaki.moviecatalogueexpert.core.data.repository.CatalogueRepository
import com.setiaki.moviecatalogueexpert.core.data.repository.DetailRepository
import com.setiaki.moviecatalogueexpert.core.domain.irepository.ICatalogueRepository
import com.setiaki.moviecatalogueexpert.core.domain.irepository.IDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class CoreModule {
    @Singleton
    @Provides
    fun provideTMDBWebservice(): TMDBWebservice {
        return TMDBWebservice.create()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideICatalogueRepository(
        webservice: TMDBWebservice,
        database: AppDatabase
    ): ICatalogueRepository {
        return CatalogueRepository(webservice, database)
    }

    @Singleton
    @Provides
    fun provideIDetailRepository(
        webservice: TMDBWebservice,
        database: AppDatabase
    ): IDetailRepository {
        return DetailRepository(webservice, database)
    }


}