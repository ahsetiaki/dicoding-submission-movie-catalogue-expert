package com.setiaki.moviecatalogueexpert.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.setiaki.moviecatalogueexpert.R
import com.setiaki.moviecatalogueexpert.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeSectionPagerAdapter: HomeSectionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.top_rated)

        bindTabLayout()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                val uri = Uri.parse("moviecatalogueexpert://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> true
        }
    }

    private fun bindTabLayout() {
        homeSectionPagerAdapter = HomeSectionPagerAdapter(this)
        val viewPager2 = binding.vp2Catalogue
        viewPager2.adapter = homeSectionPagerAdapter
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