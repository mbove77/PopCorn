package com.bove.martin.popcorn.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bove.martin.popcorn.model.Movie
import com.bove.martin.popcorn.repository.MovieRepository

class MoviesViewModel : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()
    private val popularMovies: LiveData<List<Movie>>

    init {
        popularMovies = movieRepository.popularMovies!!
    }

    fun getPopularMovies(): LiveData<List<Movie>> = popularMovies
}