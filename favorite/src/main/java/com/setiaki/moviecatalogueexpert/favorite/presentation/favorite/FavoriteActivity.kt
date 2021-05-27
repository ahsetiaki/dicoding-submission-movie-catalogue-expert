package com.setiaki.moviecatalogueexpert.favorite.presentation.favorite

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.setiaki.moviecatalogueexpert.R
import com.setiaki.moviecatalogueexpert.favorite.databinding.ActivityFavoriteBinding


class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteSectionPagerAdapter: FavoriteSectionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.favorite)

        bindTabLayout()

    }

    private fun bindTabLayout() {
        favoriteSectionPagerAdapter = FavoriteSectionPagerAdapter(this)
        val viewPager2 = binding.vp2Catalogue
        viewPager2.adapter = favoriteSectionPagerAdapter
        val tabs = binding.tabsCatalogue
        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_show
        )
    }
}