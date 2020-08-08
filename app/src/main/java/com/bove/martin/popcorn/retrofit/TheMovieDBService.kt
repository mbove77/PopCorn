package com.bove.martin.popcorn.retrofit

import com.bove.martin.popcorn.model.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Martín Bove on 07-Aug-20.
 * E-mail: mbove77@gmail.com
 */
interface TheMovieDBService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMovieResponse>

}