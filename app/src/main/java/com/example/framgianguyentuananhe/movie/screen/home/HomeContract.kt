package com.example.framgianguyentuananhe.movie.screen.home

import com.example.framgianguyentuananhe.movie.data.model.Genre
import com.example.framgianguyentuananhe.movie.data.model.Movie

interface HomeContract {

    interface View {

        fun showLoadingProgress()

        fun hideLoadingProgress()

        fun showGenre(genres: List<Genre.Data>?)

        fun showMovies(movies: List<Movie>?)
    }

    interface Action {

        fun getGenres()

        fun getMovies()

        fun getMoviesByGenre(query: String, page: Int)
    }
}
