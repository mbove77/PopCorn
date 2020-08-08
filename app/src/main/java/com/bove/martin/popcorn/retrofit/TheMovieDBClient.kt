package com.bove.martin.popcorn.retrofit

import com.bove.martin.popcorn.Commons.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Martín Bove on 07-Aug-20.
 * E-mail: mbove77@gmail.com
 */
class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private val retrofit: Retrofit

    //Singleton
    companion object {
        var instance: TheMovieDBClient? = null
            get() {
                if(field == null){
                    instance = TheMovieDBClient()
                }
                return field
            }
    }


    init {
        //Interceptor
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(ApiKeyInterceptor())
        //Cliente
        val cliente = okHttpClientBuilder.build()

        //Retrofit con cliente e interceptor
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        //Servicio de Retrofit
        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getMovieDBService() = theMovieDBService
}