package com.example.framgianguyentuananhe.movie.screen.home

import com.example.framgianguyentuananhe.movie.data.model.Genre
import com.example.framgianguyentuananhe.movie.data.repositories.GenreRepository
import com.example.framgianguyentuananhe.movie.data.repositories.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val mView: HomeContract.View,
    private val mGenreRepository: GenreRepository,
    private val mMovieRepository: MovieRepository
) : HomeContract.Action {

    override fun getMoviesByGenre(query: String, page: Int) {
        mSubcription.add(mMovieRepository.getMovieByGenre(query, page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mView.showLoadingProgress() }
            .subscribe({
                mView.hideLoadingProgress()
                mView.showMovies(it.mMovies)
            }, {
                mView.hideLoadingProgress()
            })
        )
    }

    val mSubcription: CompositeDisposable = CompositeDisposable()

    override fun getGenres() {
        val mData: ArrayList<Genre.Data>? = ArrayList()

        mSubcription.add(mGenreRepository.getMovieGenres()
            .subscribeOn(Schedulers.newThread())
            .flatMap {
                mData!!.addAll(it.mDatas!!)
                mGenreRepository.getTVGenres()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (item in it.mDatas!!) {
                    if (!mData!!.contains(item)) {
                        mData.add(item)
                    }
                }
                mView.showGenre(mData)
            }, {
                mView.showGenre(mData)
            })
        )
    }

    override fun getMovies() {
        mSubcription.add(mMovieRepository.getTrending()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { mView.showLoadingProgress() }
            .subscribe({
                mView.hideLoadingProgress()
                mView.showMovies(it.mMovies)
            }, {
                mView.hideLoadingProgress()
            })
        )
    }
}
