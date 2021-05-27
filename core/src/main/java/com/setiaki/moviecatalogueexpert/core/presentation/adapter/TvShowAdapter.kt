package com.setiaki.moviecatalogueexpert.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setiaki.moviecatalogueexpert.core.data.remote.api.TMDBWebservice
import com.setiaki.moviecatalogueexpert.core.databinding.ItemCatalogueBinding
import com.setiaki.moviecatalogueexpert.core.domain.model.TvShowModel
import com.setiaki.moviecatalogueexpert.core.presentation.listener.CatalogueOnClickListener


class TvShowAdapter(
    private val listener: CatalogueOnClickListener? = null
) : PagingDataAdapter<TvShowModel, TvShowAdapter.TVShowViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowViewHolder {
        val binding =
            ItemCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }

    }

    inner class TVShowViewHolder(private val binding: ItemCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowModel) {
            with(binding) {
                Glide.with(root.context)
                    .load("${TMDBWebservice.IMAGE_URL}${tvShow.posterPath}")
                    .into(imgPoster)
                tvTitle.text = tvShow.title
                root.setOnClickListener { listener?.onItemClicked(tvShow.id) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowModel>() {
            override fun areItemsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}