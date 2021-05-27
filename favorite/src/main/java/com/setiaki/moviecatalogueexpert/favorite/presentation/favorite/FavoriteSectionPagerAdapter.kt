package com.setiaki.moviecatalogueexpert.favorite.presentation.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.setiaki.moviecatalogueexpert.favorite.presentation.catalogue.FavoriteCatalogueFragment


class FavoriteSectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                FavoriteCatalogueFragment.newInstance(FavoriteCatalogueFragment.TYPE_FAVORITE_MOVIE)
            1 -> fragment =
                FavoriteCatalogueFragment.newInstance(FavoriteCatalogueFragment.TYPE_FAVORITE_TV_SHOW)
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}