package com.example.framgianguyentuananhe.movie.data.source

import com.example.framgianguyentuananhe.movie.data.model.SearchResult
import io.reactivex.Observable

interface MovieDataSource {

    interface Remote {

        fun getTrending(): Observable<SearchResult>

        fun getMovieByGenre(query: String, page: Int): Observable<SearchResult>
    }
}
