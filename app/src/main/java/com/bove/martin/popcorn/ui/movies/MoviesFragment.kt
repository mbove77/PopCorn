package com.bove.martin.popcorn.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bove.martin.popcorn.R
import com.bove.martin.popcorn.adapters.MovieRecyclerViewAdapter
import com.bove.martin.popcorn.model.Movie
import kotlinx.android.synthetic.main.fragment_movies.*
import java.util.ArrayList

class MoviesFragment : Fragment() {

    private lateinit var moviewViewModel: MoviesViewModel
    private lateinit var movieAdapter: MovieRecyclerViewAdapter
    private var popularMovies: List<Movie> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        moviewViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movies, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieRecyclerViewAdapter(popularMovies)
        movieRecyclerView.apply {
            layoutManager = (GridLayoutManager(activity, 2))
            adapter = movieAdapter
        }

        moviewViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            movieAdapter.setData(popularMovies)
        })
    }

}