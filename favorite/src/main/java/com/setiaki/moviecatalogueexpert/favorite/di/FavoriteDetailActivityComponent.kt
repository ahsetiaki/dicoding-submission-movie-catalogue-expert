package com.setiaki.moviecatalogueexpert.favorite.di

import android.content.Context
import com.setiaki.moviecatalogueexpert.di.FavoriteModuleDependencies
import com.setiaki.moviecatalogueexpert.favorite.presentation.detail.FavoriteDetailActivity
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.scopes.ActivityScoped


@ActivityScoped
@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteDetailActivityComponent {
    fun inject(activity: FavoriteDetailActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteDetailActivityComponent
    }

}