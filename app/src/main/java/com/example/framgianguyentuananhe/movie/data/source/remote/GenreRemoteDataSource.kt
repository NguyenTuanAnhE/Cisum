package com.example.framgianguyentuananhe.movie.data.source.remote

import com.example.framgianguyentuananhe.movie.data.model.Genre
import com.example.framgianguyentuananhe.movie.data.source.GenreDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.api.Api
import com.example.framgianguyentuananhe.movie.utils.Constant
import io.reactivex.Observable

class GenreRemoteDataSource (private val mApi: Api) : GenreDataSource.Remote {
    override fun getTVGenres(): Observable<Genre> {
        return mApi.getTVGenres(Constant.API_KEY, Constant.LANGUAGE_ENGLISH)
    }

    override fun getMovieGenres(): Observable<Genre> {
        return mApi.getMovieGenres(Constant.API_KEY, Constant.LANGUAGE_ENGLISH)
    }

}
