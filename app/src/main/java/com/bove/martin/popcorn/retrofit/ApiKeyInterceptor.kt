package com.bove.martin.popcorn.retrofit

import com.bove.martin.popcorn.Commons.Constantes
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Martín Bove on 07-Aug-20.
 * E-mail: mbove77@gmail.com
 */
class ApiKeyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
       val urlWithParams = chain.request().url
           .newBuilder()
           .addQueryParameter("api_key", Constantes.API_KEY)
           .addQueryParameter("language", "es-ES")
           .build()

        var newRequest = chain.request()

        newRequest = newRequest.newBuilder()
            .url(urlWithParams)
            .build()

        return chain.proceed(newRequest)
    }
}