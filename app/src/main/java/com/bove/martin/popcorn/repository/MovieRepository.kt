package com.bove.martin.popcorn.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bove.martin.popcorn.Commons.MyApp
import com.bove.martin.popcorn.model.Movie
import com.bove.martin.popcorn.model.PopularMovieResponse
import com.bove.martin.popcorn.retrofit.TheMovieDBClient
import com.bove.martin.popcorn.retrofit.TheMovieDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Martín Bove on 07-Aug-20.
 * E-mail: mbove77@gmail.com
 */
class MovieRepository {
    var theMovieDBService: TheMovieDBService? = null
    var theMovieDBClient: TheMovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
       theMovieDBClient = TheMovieDBClient.instance
        theMovieDBService = theMovieDBClient?.getMovieDBService()
        popularMovies = popularMovies()
    }

    private fun popularMovies(): MutableLiveData<List<Movie>>? {
        if (popularMovies == null) popularMovies = MutableLiveData()

        val call: Call<PopularMovieResponse>? = theMovieDBService?.getPopularMovies()
        call?.enqueue(object : Callback<PopularMovieResponse> {

            override fun onResponse(call: Call<PopularMovieResponse>, response: Response<PopularMovieResponse>) {
                if (response.isSuccessful) {
                    popularMovies?.value = response.body()?.results
                } else {
                    Toast.makeText(MyApp.instance, "Error en la respuesta de la llamada", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_SHORT).show()
            }

        })

        return popularMovies
    }

}