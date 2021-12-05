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
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movierate.R
import com.example.movierate.api.objects.Movie
import com.example.movierate.databinding.BoxOfficePageBinding
import com.example.movierate.extensions.createToast
import com.example.movierate.extensions.listenError
import com.example.movierate.extensions.setData
import com.example.movierate.ui.adapters.BoxOfficeListAdapter
import com.example.movierate.ui.viewmodel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.android.ext.android.inject

class PageAboutBoxOffice : Fragment(R.layout.box_office_page) {

    private val viewModel: MovieViewModel by inject()

    private val binding: BoxOfficePageBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListDelegationAdapter(BoxOfficeListAdapter().boxOfficeDelegateAdapter())

        val bottom: BottomNavigationView = requireActivity().findViewById(R.id.bottomNav)

        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.popularMoviesMenu -> {
                    findNavController().navigate(R.id.action_boxOfficePage_to_popular)
                }
                R.id.seeNowMenu -> {
                    findNavController().navigate(R.id.action_boxOfficePage_to_moviesNowFragmet)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        (activity as AppCompatActivity).supportActionBar?.title = "Box Office"

        binding.apply {
            progressBar.visibility = VISIBLE
            progressBackground.visibility = VISIBLE
            popularList.adapter = adapter
            popularList.layoutManager = LinearLayoutManager(
                view.context, LinearLayoutManager.VERTICAL, false
            )
            if (viewModel.boxOffice.value.isNullOrEmpty()) {
                viewModel.getMoviesFromBoxOffice()
            }
            refreshLayout.setOnRefreshListener {
                viewModel.getMoviesFromBoxOffice()
            }
            loadMovies(adapter, progressBar, refreshLayout, popularList, progressBackground)
        }
    }

    private fun loadMovies(
        adapter: ListDelegationAdapter<List<Movie>>,
        progressBar: ProgressBar,
        refreshLayout: SwipeRefreshLayout,
        recyclerView: RecyclerView,
        progressBackground: View
    ) {
        viewModel.boxOffice.observe(viewLifecycleOwner, { value ->
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