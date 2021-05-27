package com.setiaki.moviecatalogueexpert.favorite.di

import android.content.Context
import com.setiaki.moviecatalogueexpert.di.FavoriteModuleDependencies
import com.setiaki.moviecatalogueexpert.favorite.presentation.catalogue.FavoriteCatalogueFragment
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.scopes.FragmentScoped


@FragmentScoped
@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteCatalogueFragmentComponent {
    fun inject(fragment: FavoriteCatalogueFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteCatalogueFragmentComponent
    }
}