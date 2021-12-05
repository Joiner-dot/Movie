package com.example.movierate.ui.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.viewbinding.library.activity.viewBinding
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierate.R
import com.example.movierate.databinding.SearchMovieBinding
import com.example.movierate.extensions.setData
import com.example.movierate.ui.adapters.SearchMovieAdapter
import com.example.movierate.ui.viewmodel.MovieViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.android.ext.android.inject

class SearchMoviePage : AppCompatActivity() {

    private val viewModel: MovieViewModel by inject()

    private val binding: SearchMovieBinding by viewBinding()

    private val adapter = ListDelegationAdapter(SearchMovieAdapter().searchMovieAdapter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            title = "Search"
            setDisplayHomeAsUpEnabled(true)
        }
        setContentView(binding.root)

        val movie: String = intent.getStringExtra("STR").toString()
        binding.apply {
            searchMovies.adapter = adapter
            searchMovies.layoutManager = LinearLayoutManager(
                applicationContext, LinearLayoutManager.VERTICAL, false
            )
        }
        if (viewModel.searchMovies.value.isNullOrEmpty()) {
            viewModel.getSearchMovies(movie)
        }
        loadSearch()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView: SearchView = searchItem?.actionView as SearchView


        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchMovies(query)
                loadSearch()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun loadSearch() {
        binding.apply {
            progressBar2.visibility = VISIBLE
            searchMovies.visibility = INVISIBLE
            progressBackground.visibility = VISIBLE
            viewModel.searchMovies.observe(this@SearchMoviePage, { value ->
                getAwayProgressBar(progressBar2, progressBackground)
                if (!value.isNullOrEmpty()) {
                    adapter.setData(value)
                    searchMovies.visibility = VISIBLE
                } else {
                    createToast()
                }
            })
        }
    }

    private fun createToast(message: String = "Unable to get data") {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun getAwayProgressBar(progressBar: ProgressBar, progressBackground: View) {
        progressBar.visibility = INVISIBLE
        progressBackground.visibility = INVISIBLE
    }
}