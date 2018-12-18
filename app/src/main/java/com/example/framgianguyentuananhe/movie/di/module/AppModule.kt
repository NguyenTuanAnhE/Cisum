package com.example.framgianguyentuananhe.movie.di.module

import android.content.Context
import com.example.framgianguyentuananhe.movie.App
import com.example.framgianguyentuananhe.movie.data.repositories.GenreRepository
import com.example.framgianguyentuananhe.movie.data.repositories.MovieRepository
import com.example.framgianguyentuananhe.movie.data.source.remote.GenreRemoteDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.MovieRemoteDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.api.Api
import com.example.framgianguyentuananhe.movie.data.source.remote.api.ServiceGenerator
import com.example.framgianguyentuananhe.movie.screen.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module()
class AppModule(var mApp: App) {

    @Provides
    fun provideContext(): Context = mApp.applicationContext

    @Provides
    fun provideApi(context: Context): Api =
        ServiceGenerator.createMovieService(context).create(Api::class.java)

    @Provides
    fun provideGenreRepositoy(remote: GenreRemoteDataSource): GenreRepository =
        GenreRepository(remote)

    @Provides
    fun provideMovieRepository(remote: MovieRemoteDataSource): MovieRepository =
        MovieRepository(remote)

//    @Provides
//    fun provideMovieRemoteDataSource(api: Api): MovieRemoteDataSource = MovieRemoteDataSource(api)
//
//    @Provides
//    fun provideGenreRemoteDataSource(api: Api): GenreRemoteDataSource = GenreRemoteDataSource(api)

    @Provides
    fun provideHomePresenter(
        genreRepository: GenreRepository,
        movieRepository: MovieRepository
    ): HomePresenter = HomePresenter(genreRepository, movieRepository)
}
