package com.example.framgianguyentuananhe.movie.data.source.remote.api

import android.content.Context
import com.example.framgianguyentuananhe.movie.BuildConfig
import com.example.framgianguyentuananhe.movie.utils.Constant
import com.example.framgianguyentuananhe.movie.utils.Constant.REQUEST_TIMEOUT
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private fun provideInterceptor(): Interceptor = MovieInterceptor()

    private fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//        .excludeFieldsWithoutExposeAnnotation()
        .create()


    private fun provideCache(context: Context): Cache {
        val cacheSize = 24L * 24 * 1000;
        return Cache(context.cacheDir, cacheSize)
    }

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.cache(provideCache(context))
            .addInterceptor(provideInterceptor())
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }

    private fun createMovieService(context: Context): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.END_POINT_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .client(provideOkHttpClient(context))
        .build()

    fun getApi(context: Context): Api = createMovieService(context).create(Api::class.java)
}
