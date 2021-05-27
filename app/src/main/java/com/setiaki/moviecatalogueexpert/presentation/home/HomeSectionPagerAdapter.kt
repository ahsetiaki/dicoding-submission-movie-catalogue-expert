package com.setiaki.moviecatalogueexpert.presentation.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.setiaki.moviecatalogueexpert.presentation.catalogue.TopRatedCatalogueFragment

class HomeSectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = TopRatedCatalogueFragment.newInstance(TopRatedCatalogueFragment.TYPE_TOP_RATED_MOVIE)
            1 -> fragment = TopRatedCatalogueFragment.newInstance(TopRatedCatalogueFragment.TYPE_TOP_RATED_TV_SHOW)
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}