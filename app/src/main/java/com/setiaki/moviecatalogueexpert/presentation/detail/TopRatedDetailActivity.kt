package com.setiaki.moviecatalogueexpert.presentation.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.setiaki.moviecatalogueexpert.R
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice
import com.setiaki.moviecatalogueexpert.core.domain.model.GenreModel
import com.setiaki.moviecatalogueexpert.databinding.ActivityTopRatedDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopRatedDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTopRatedDetailBinding

    private val topRatedDetailViewModel: TopRatedDetailViewModel by viewModels()

    private var type: String? = ""
    private var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopRatedDetailBinding.inflate(layoutInflater)
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

    private fun bindDetail() {
        with(binding) {
            if (type == TYPE_MOVIE) {
                topRatedDetailViewModel.getMovieDetail(itemId)
                    .observe(this@TopRatedDetailActivity, { resource ->
                        val movie = resource.data
                        supportActionBar?.title = movie?.title

                        Glide.with(root.context)
                            .load("${TMDBWebservice.IMAGE_URL}${movie?.posterPath}")
                            .into(imgPoster)
                        tvTitle.text = movie?.title
                        tvReleaseDate.text = movie?.releaseDate
                        tvGenres.text = convertGenres(movie?.genres)
                        tvVoteAverage.text = movie?.voteAverage.toString()
                        tvOverview.text = movie?.overview
                        fabFavorite.setImageDrawable(getFavoriteButtonDrawable(movie?.isFavorited!!))
                        fabFavorite.setOnClickListener(this@TopRatedDetailActivity)

                    })
            } else {
                topRatedDetailViewModel.getTvShowDetail(itemId)
                    .observe(this@TopRatedDetailActivity, { resource ->
                        val tvShow = resource.data
                        supportActionBar?.title = tvShow?.title
                        Glide.with(root.context)
                            .load("${TMDBWebservice.IMAGE_URL}${tvShow?.posterPath}")
                            .into(imgPoster)
                        tvTitle.text = tvShow?.title
                        tvReleaseDate.text = tvShow?.releaseDate
                        tvGenres.text = convertGenres(tvShow?.genres)
                        tvVoteAverage.text = tvShow?.voteAverage.toString()
                        tvOverview.text = tvShow?.overview
                        fabFavorite.setImageDrawable(getFavoriteButtonDrawable(tvShow?.isFavorited!!))
                        fabFavorite.setOnClickListener(this@TopRatedDetailActivity)
                    })
            }
        }

    }

    private fun convertGenres(genreResponses: List<GenreModel>?): String {
        val listGenreName = arrayListOf<String?>()
        genreResponses?.forEach { listGenreName.add(it.name) }
        return listGenreName.joinToString()
    }

    private fun toggleFavoriteButton() {
        if (type == TYPE_MOVIE) {
            topRatedDetailViewModel.toggleMovieFavoriteStatus(itemId)
        } else {
            topRatedDetailViewModel.toggleTvShowFavoriteStatus(itemId)
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