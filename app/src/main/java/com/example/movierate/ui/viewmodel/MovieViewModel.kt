package com.example.movierate.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierate.api.objects.Movie
import com.example.movierate.interactors.interfaces.MovieInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class MovieViewModel(private val movieInteractor: MovieInteractor) : ViewModel() {

    val listMovies = MutableLiveData<List<Movie>>()

    val boxOffice = MutableLiveData<List<Movie>>()

    val searchMovies = MutableLiveData<List<Movie>>()

    val randomMovies = MutableLiveData<List<Movie>>()

    val errorMessage = MutableLiveData<String>()

    fun getMoviesFromBoxOffice() {
        viewModelScope.launch(Dispatchers.Main) {
            val movies = withContext(Dispatchers.IO) {
                try {
                    movieInteractor.getBoxOfficeMovies()
                } catch (e: Exception) {
                    errorMessage.postValue("Box Office Movies error")
                    emptyList()
                }
            }
            boxOffice.value = movies
        }
    }

    fun getRandomMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            val movies = withContext(Dispatchers.IO) {
                try {
                    movieInteractor.getRandom()
                } catch (e: Exception) {
                    errorMessage.postValue("Random Movies error")
                    emptyList()
                }
            }
            randomMovies.value = movies
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            val movies = withContext(Dispatchers.IO) {
                try {
                    movieInteractor.getPopularMovies()
                } catch (e: Exception) {
                    errorMessage.postValue("Popular Movies error")
                    emptyList()
                }
            }
            listMovies.value = movies
        }
    }

    fun getSearchMovies(movie: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val movies = withContext(Dispatchers.IO) {
                try {
                    movieInteractor.getSearchMovie(movie)
                } catch (e: Exception) {
                    errorMessage.postValue("Search Movies error")
                    emptyList()
                }
            }
            searchMovies.value = movies
        }
    }
}