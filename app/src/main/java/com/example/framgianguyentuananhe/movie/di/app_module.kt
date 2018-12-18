package com.example.framgianguyentuananhe.movie.di

import com.example.framgianguyentuananhe.movie.App
import com.example.framgianguyentuananhe.movie.data.repositories.GenreRepository
import com.example.framgianguyentuananhe.movie.data.repositories.MovieRepository
import com.example.framgianguyentuananhe.movie.data.source.GenreDataSource
import com.example.framgianguyentuananhe.movie.data.source.MovieDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.GenreRemoteDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.MovieRemoteDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.api.Api
import com.example.framgianguyentuananhe.movie.data.source.remote.api.ServiceGenerator
import com.example.framgianguyentuananhe.movie.screen.home.HomePresenter
import org.koin.dsl.module.module

val appModule = module {

    single { ServiceGenerator.createMovieService(get()).create(Api::class.java) as Api }

    single { GenreRemoteDataSource(get()) as GenreDataSource.Remote }

    single { MovieRemoteDataSource(get()) as MovieDataSource.Remote }

    single { GenreRepository(get()) }

    single { MovieRepository(get()) }

    factory { HomePresenter(get(), get()) }
}
