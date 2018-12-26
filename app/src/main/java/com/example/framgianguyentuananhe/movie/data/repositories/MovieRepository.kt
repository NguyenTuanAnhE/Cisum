package com.example.framgianguyentuananhe.movie.data.repositories

import com.example.framgianguyentuananhe.movie.data.model.SearchResult
import com.example.framgianguyentuananhe.movie.data.source.MovieDataSource
import io.reactivex.Observable

class MovieRepository (private val mMovieDataSource: MovieDataSource.Remote) : MovieDataSource.Remote {

    override fun getMovieByGenre(query: String, page: Int): Observable<SearchResult> =
        mMovieDataSource.getMovieByGenre(query, page)

    override fun getTrending(): Observable<SearchResult> =
        mMovieDataSource.getTrending()
}
