package com.example.framgianguyentuananhe.movie.data.source.remote.api

import okhttp3.Interceptor
import okhttp3.Response

import java.io.IOException

class MovieInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .header("Accept", "application/json")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Cache-Control", "no-store")
            .method(request.method(), request.body())
            .build()
        return chain.proceed(request)
    }

}
