package com.example.framgianguyentuananhe.movie.data.repositories

import com.example.framgianguyentuananhe.movie.data.model.Genre
import com.example.framgianguyentuananhe.movie.data.source.GenreDataSource
import io.reactivex.Observable

class GenreRepository(private val mGenreDataSource: GenreDataSource.Remote) :
    GenreDataSource.Remote {

    override fun getTVGenres(): Observable<Genre> =
        mGenreDataSource.getTVGenres()

    override fun getMovieGenres(): Observable<Genre> =
        mGenreDataSource.getMovieGenres()

}
