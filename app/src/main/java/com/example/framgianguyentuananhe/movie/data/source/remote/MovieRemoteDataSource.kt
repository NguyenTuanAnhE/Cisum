package com.example.framgianguyentuananhe.movie.data.source.remote

import com.example.framgianguyentuananhe.movie.data.model.SearchResult
import com.example.framgianguyentuananhe.movie.data.source.MovieDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.api.Api
import com.example.framgianguyentuananhe.movie.utils.Constant
import io.reactivex.Observable

class MovieRemoteDataSource(private val mApi: Api) : MovieDataSource.Remote {

    override fun getMovieByGenre(query: String, page: Int): Observable<SearchResult> =
        mApi.getMovieByGenre(Constant.API_KEY, Constant.LANGUAGE_ENGLISH, query, page, false)

    override fun getTrending(): Observable<SearchResult> = mApi.getTrendingMovies(Constant.API_KEY)
}
