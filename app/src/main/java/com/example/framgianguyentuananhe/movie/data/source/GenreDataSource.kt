package com.example.framgianguyentuananhe.movie.data.source

import com.example.framgianguyentuananhe.movie.data.model.Genre
import io.reactivex.Observable

interface GenreDataSource {

    interface Remote {
        fun getMovieGenres(): Observable<Genre>

        fun getTVGenres(): Observable<Genre>
    }
}
