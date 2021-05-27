package com.setiaki.moviecatalogueexpert.favorite.presentation.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.setiaki.moviecatalogueexpert.favorite.R
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice
import com.setiaki.moviecatalogueexpert.core.domain.model.GenreModel
import com.setiaki.moviecatalogueexpert.di.FavoriteModuleDependencies
import com.setiaki.moviecatalogueexpert.favorite.databinding.ActivityFavoriteDetailBinding
import com.setiaki.moviecatalogueexpert.favorite.di.DaggerFavoriteDetailActivityComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityFavoriteDetailBinding

    @Inject
    lateinit var favoriteDetailViewModel: FavoriteDetailViewModel

    private var type: String? = ""
    private var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()

        binding = ActivityFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra(EXTRA_TYPE)
        itemId = intent.getIntExtra(EXTRA_ITEM_ID, 0)

        bindDetail()

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.fab_favorite) {
            toggleFavoriteButton()
        }
    }

    internal fun inject() {
        DaggerFavoriteDetailActivityComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    private fun bindDetail() {
        if (type == TYPE_MOVIE) {
            favoriteDetailViewModel.getMovieDetail(itemId).observe(this, { resource ->
                val movie = resource.data
                supportActionBar?.title = movie?.title
                with(binding) {
                    Glide.with(root.context)
                        .load("${TMDBWebservice.IMAGE_URL}${movie?.posterPath}")
                        .into(imgPoster)
                    tvTitle.text = movie?.title
                    tvReleaseDate.text = movie?.releaseDate
                    tvGenres.text = convertGenres(movie?.genres)
                    tvVoteAverage.text = movie?.voteAverage.toString()
                    tvOverview.text = movie?.overview
                    fabFavorite.setImageDrawable(getFavoriteButtonDrawable(movie?.isFavorited!!))
                    binding.fabFavorite.setOnClickListener(this@FavoriteDetailActivity)
                }
            })
        } else {
            favoriteDetailViewModel.getTvShowDetail(itemId).observe(this, { resource ->
                val tvShow = resource.data
                supportActionBar?.title = tvShow?.title
                with(binding) {
                    Glide.with(root.context)
                        .load("${TMDBWebservice.IMAGE_URL}${tvShow?.posterPath}")
                        .into(imgPoster)
                    tvTitle.text = tvShow?.title
                    tvReleaseDate.text = tvShow?.releaseDate
                    tvGenres.text = convertGenres(tvShow?.genres)
                    tvVoteAverage.text = tvShow?.voteAverage.toString()
                    tvOverview.text = tvShow?.overview
                    fabFavorite.setImageDrawable(getFavoriteButtonDrawable(tvShow?.isFavorited!!))
                    binding.fabFavorite.setOnClickListener(this@FavoriteDetailActivity)
                }
            })
        }
    }

    private fun convertGenres(genreResponses: List<GenreModel>?): String {
        val listGenreName = arrayListOf<String?>()
        genreResponses?.forEach { listGenreName.add(it.name) }
        return listGenreName.joinToString()
    }

    private fun toggleFavoriteButton() {
        if (type == TYPE_MOVIE) {
            favoriteDetailViewModel.toggleMovieFavoriteStatus(itemId)
        } else {
            favoriteDetailViewModel.toggleTvShowFavoriteStatus(itemId)
        }
    }

    private fun getFavoriteButtonDrawable(isFavorited: Boolean): Drawable? {
        return if (isFavorited) {
            ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_filled, null)
        } else {
            ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_border, null)
        }
    }

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_ITEM_ID = "extra_item_id"
        const val TYPE_MOVIE = "type_movie"
        const val TYPE_TV_SHOW = "type_tv_show"
    }


}