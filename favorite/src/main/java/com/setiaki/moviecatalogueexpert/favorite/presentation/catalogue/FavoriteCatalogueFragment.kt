package com.setiaki.moviecatalogueexpert.favorite.presentation.catalogue

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.setiaki.moviecatalogueexpert.core.presentation.adapter.MovieAdapter
import com.setiaki.moviecatalogueexpert.core.presentation.adapter.TvShowAdapter
import com.setiaki.moviecatalogueexpert.core.presentation.listener.CatalogueOnClickListener
import com.setiaki.moviecatalogueexpert.di.FavoriteModuleDependencies
import com.setiaki.moviecatalogueexpert.favorite.databinding.FragmentFavoriteCatalogueBinding
import com.setiaki.moviecatalogueexpert.favorite.di.DaggerFavoriteCatalogueFragmentComponent
import com.setiaki.moviecatalogueexpert.favorite.presentation.detail.FavoriteDetailActivity
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoriteCatalogueFragment : Fragment(), CatalogueOnClickListener {
    private var _binding: FragmentFavoriteCatalogueBinding? = null
    private val binding get() = _binding as FragmentFavoriteCatalogueBinding

    @Inject
    lateinit var favoriteCatalogueViewModel: FavoriteCatalogueViewModel

    private var type: String? = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

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
        _binding = FragmentFavoriteCatalogueBinding.inflate(inflater, container, false)
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
            if (type == TYPE_FAVORITE_MOVIE) {
                FavoriteDetailActivity.TYPE_MOVIE
            } else {
                FavoriteDetailActivity.TYPE_TV_SHOW
            }

        val toDetailActivityIntent = Intent(activity, FavoriteDetailActivity::class.java).apply {
            putExtra(FavoriteDetailActivity.EXTRA_TYPE, detailActivityType)
            putExtra(FavoriteDetailActivity.EXTRA_ITEM_ID, itemId)
        }

        activity?.startActivity(toDetailActivityIntent)
    }

    internal fun inject() {
        DaggerFavoriteCatalogueFragmentComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    private fun setRecyclerView() {
        val mAdapter =
            if (type == TYPE_FAVORITE_MOVIE) {
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
        if (type == TYPE_FAVORITE_MOVIE) {
            favoriteCatalogueViewModel.getFavoritedMovies()
                .observe(viewLifecycleOwner, { pagingMovies ->
                    lifecycleScope.launch {
                        (binding.rvCatalogue.adapter as MovieAdapter).submitData(pagingMovies)
                    }
                })
        } else {
            favoriteCatalogueViewModel.getFavoritedTvShows()
                .observe(viewLifecycleOwner, { pagingTvShows ->
                    lifecycleScope.launch {
                        (binding.rvCatalogue.adapter as TvShowAdapter).submitData(pagingTvShows)
                    }
                })

        }
    }

    companion object {
        private const val EXTRA_TYPE = "extra_type"
        const val TYPE_FAVORITE_MOVIE = "type_favorite_movie"
        const val TYPE_FAVORITE_TV_SHOW = "type_favorite_tv_show"

        @JvmStatic
        fun newInstance(type: String) =
            FavoriteCatalogueFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_TYPE, type)
                }
            }
    }


}