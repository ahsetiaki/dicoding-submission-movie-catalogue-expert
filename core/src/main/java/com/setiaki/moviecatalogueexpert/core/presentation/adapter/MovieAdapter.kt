package com.setiaki.moviecatalogueexpert.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice
import com.setiaki.moviecatalogueexpert.core.databinding.ItemCatalogueBinding
import com.setiaki.moviecatalogueexpert.core.domain.model.MovieModel
import com.setiaki.moviecatalogueexpert.core.presentation.listener.CatalogueOnClickListener


class MovieAdapter(
    private val listener: CatalogueOnClickListener? = null
) : PagingDataAdapter<MovieModel, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding =
            ItemCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }

    }

    inner class MovieViewHolder(private val binding: ItemCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            with(binding) {
                Glide.with(root.context)
                    .load("${TMDBWebservice.IMAGE_URL}${movie.posterPath}")
                    .into(imgPoster)
                tvTitle.text = movie.title
                root.setOnClickListener { listener?.onItemClicked(movie.id) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}