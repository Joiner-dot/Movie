package com.example.movierate.ui.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import android.viewbinding.library.fragment.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.movierate.R
import com.example.movierate.databinding.MoviesNowFragmetBinding
import com.example.movierate.extensions.listenError
import com.example.movierate.ui.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject
import java.net.NetworkInterface
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.apply {
            title = "Popular"
            setDisplayHomeAsUpEnabled(false)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val info = manager.connectionInfo
        Log.d("TESTST", NetworkInterface.getNetworkInterfaces().nextElement().toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView: SearchView = searchItem?.actionView as SearchView

        listenError(viewModel.errorMessage)

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    val intent = Intent(applicationContext, SearchMoviePage::class.java)
                    intent.putExtra("STR", query)
                    startActivity(intent)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}