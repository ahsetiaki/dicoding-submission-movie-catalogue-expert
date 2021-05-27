package com.setiaki.moviecatalogueexpert.presentation.catalogue

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.setiaki.moviecatalogueexpert.core.presentation.adapter.MovieAdapter
import com.setiaki.moviecatalogueexpert.core.presentation.adapter.TvShowAdapter
import com.setiaki.moviecatalogueexpert.core.presentation.listener.CatalogueOnClickListener
import com.setiaki.moviecatalogueexpert.databinding.FragmentTopRatedCatalogueBinding
import com.setiaki.moviecatalogueexpert.presentation.detail.TopRatedDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TopRatedCatalogueFragment : Fragment(), CatalogueOnClickListener {
    private var _binding: FragmentTopRatedCatalogueBinding? = null
    private val binding get() = _binding as FragmentTopRatedCatalogueBinding

    private val topRatedCatalogueViewModel: TopRatedCatalogueViewModel by activityViewModels()

    private var type: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            type = it.getString(EXTRA_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedCatalogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        populateRecyclerViewAdapterData()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(itemId: Int) {
        val detailActivityType =
            if (type == TYPE_TOP_RATED_MOVIE) {
                TopRatedDetailActivity.TYPE_MOVIE
            } else {
                TopRatedDetailActivity.TYPE_TV_SHOW
            }

        val toDetailActivityIntent = Intent(activity, TopRatedDetailActivity::class.java).apply {
            putExtra(TopRatedDetailActivity.EXTRA_TYPE, detailActivityType)
            putExtra(TopRatedDetailActivity.EXTRA_ITEM_ID, itemId)
        }

        activity?.startActivity(toDetailActivityIntent)
    }

    private fun setRecyclerView() {
        val mAdapter =
            if (type == TYPE_TOP_RATED_MOVIE) {
                MovieAdapter(this)
            } else {
                TvShowAdapter(this)
            }

        binding.rvCatalogue.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = mAdapter
        }
    }

    private fun populateRecyclerViewAdapterData() {
        if (type == TYPE_TOP_RATED_MOVIE) {
            topRatedCatalogueViewModel.getTopRatedMovies()
                .observe(viewLifecycleOwner, { pagingMovies ->
                    lifecycleScope.launch {
                        (binding.rvCatalogue.adapter as MovieAdapter).submitData(pagingMovies)
                    }
                })
        } else {
            topRatedCatalogueViewModel.getTopRatedTvShows()
                .observe(viewLifecycleOwner, { pagingTvShows ->
                    lifecycleScope.launch {
                        (binding.rvCatalogue.adapter as TvShowAdapter).submitData(pagingTvShows)
                    }
                })
        }
    }

    companion object {
        private const val EXTRA_TYPE = "extra_type"
        const val TYPE_TOP_RATED_MOVIE = "type_home_movie"
        const val TYPE_TOP_RATED_TV_SHOW = "type_home_tv_show"

        @JvmStatic
        fun newInstance(type: String) =
            TopRatedCatalogueFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_TYPE, type)
                }
            }
    }


}