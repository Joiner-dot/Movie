package com.example.movierate.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.viewbinding.library.fragment.viewBinding
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movierate.R
import com.example.movierate.api.objects.Movie
import com.example.movierate.databinding.MoviesNowFragmetBinding
import com.example.movierate.extensions.createToast
import com.example.movierate.extensions.listenError
import com.example.movierate.extensions.setData
import com.example.movierate.ui.adapters.SeeNowAdapter
import com.example.movierate.ui.viewmodel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.android.ext.android.inject


class MoviesNowFragment : Fragment(R.layout.movies_now_fragmet) {

    private val viewModel: MovieViewModel by inject()

    private val binding: MoviesNowFragmetBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListDelegationAdapter(SeeNowAdapter().seeNowMoviesDelegateAdapter())

        val bottom: BottomNavigationView = requireActivity().findViewById(R.id.bottomNav)

        (activity as AppCompatActivity).supportActionBar?.title = "Random Movies"

        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.boxOfficeMenu -> {
                    findNavController().navigate(R.id.action_moviesNowFragmet_to_boxOfficePage)
                }
                R.id.popularMoviesMenu -> {
                    findNavController().navigate(R.id.action_moviesNowFragmet_to_popular)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        binding.apply {
            seeNow.layoutManager = GridLayoutManager(binding.root.context, 2)
            seeNow.adapter = adapter
            progressBar3.visibility = VISIBLE
            progressBackground.visibility = VISIBLE
            if (viewModel.randomMovies.value.isNullOrEmpty()) {
                viewModel.getRandomMovies()
            }
            refreshLayout.setOnRefreshListener {
                viewModel.getRandomMovies()
            }
            loadMovies(adapter, progressBar3, refreshLayout, seeNow, progressBackground)
        }
    }


    private fun loadMovies(
        adapter: ListDelegationAdapter<List<Movie>>,
        progressBar: ProgressBar,
        refreshLayout: SwipeRefreshLayout,
        recyclerView: RecyclerView,
        progressBackground: View
    ) {
        viewModel.randomMovies.observe(viewLifecycleOwner, { value ->
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