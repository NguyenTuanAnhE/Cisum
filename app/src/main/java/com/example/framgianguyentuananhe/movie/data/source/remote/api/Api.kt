package com.example.framgianguyentuananhe.movie.data.source.remote.api

import com.example.framgianguyentuananhe.movie.data.model.Genre
import com.example.framgianguyentuananhe.movie.data.model.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("genre/movie/list")
    fun getMovieGenres(@Query("api_key") apiKey: String, @Query("language") lan: String): Observable<Genre>

    @GET("genre/tv/list")
    fun getTVGenres(@Query("api_key") apiKey: String, @Query("language") lan: String): Observable<Genre>

    @GET("trending/all/day")
    fun getTrendingMovies(@Query("api_key") apiKey: String): Observable<SearchResult>

    @GET("search/movie")
    fun getMovieByGenre(
        @Query("api_key") apiKey: String,
        @Query("language") lan: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean
    ): Observable<SearchResult>
}
