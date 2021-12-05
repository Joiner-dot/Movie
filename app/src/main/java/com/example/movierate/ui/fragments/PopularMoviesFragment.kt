package com.example.movierate.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.viewbinding.library.fragment.viewBinding
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movierate.ui.adapters.PopularMoviesAdapter
import com.example.movierate.R
import com.example.movierate.api.objects.Movie
import com.example.movierate.databinding.PopularMoviesBinding
import com.example.movierate.extensions.createToast
import com.example.movierate.extensions.listenError
import com.example.movierate.extensions.setData
import com.example.movierate.ui.viewmodel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.android.ext.android.inject


class PopularMoviesFragment : Fragment(R.layout.popular_movies) {

    private val viewModel: MovieViewModel by inject()

    private val binding: PopularMoviesBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListDelegationAdapter(PopularMoviesAdapter().popularMoviesAdapterDelegate())

        val bottom: BottomNavigationView = requireActivity().findViewById(R.id.bottomNav)

        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.boxOfficeMenu -> {
                    findNavController().navigate(R.id.action_popular_to_boxOfficePage)
                }
                R.id.seeNowMenu -> {
                    findNavController().navigate(R.id.action_popular_to_moviesNowFragmet)
                }
                R.id.popularMoviesMenu -> {
                }
            }
            return@setOnNavigationItemSelectedListener true
        }


        bottom.selectedItemId = R.id.popularMoviesMenu
        (activity as AppCompatActivity).supportActionBar?.title = "Popular"

        binding.apply {
            progressBar.visibility = VISIBLE
            progressBackground.visibility = VISIBLE
            popularList.adapter = adapter
            popularList.layoutManager = LinearLayoutManager(
                view.context, LinearLayoutManager.VERTICAL, false
            )
            if (viewModel.listMovies.value.isNullOrEmpty()) {
                viewModel.getPopularMovies()
            }
            loadMovies(adapter, progressBar, refreshLayout, popularList, progressBackground)
            refreshLayout.setOnRefreshListener {
                viewModel.getPopularMovies()
            }
        }
    }

    private fun loadMovies(
        adapter: ListDelegationAdapter<List<Movie>>,
        progressBar: ProgressBar,
        refreshLayout: SwipeRefreshLayout,
        recyclerView: RecyclerView,
        progressBackground: View
    ) {
        viewModel.listMovies.observe(viewLifecycleOwner, { value ->
            getAwayProgressBar(progressBar, refreshLayout, progressBackground)
            adapter.setData(value)
            recyclerView.visibility = VISIBLE
        })
    }

    private fun getAwayProgressBar(
        progressBar: ProgressBar,
        refreshLayout: SwipeRefreshLayout,
        progressBackground: View
    ) {
        refreshLayout.isRefreshing = false
        progressBar.visibility = INVISIBLE
        progressBackground.visibility = INVISIBLE
    }
}