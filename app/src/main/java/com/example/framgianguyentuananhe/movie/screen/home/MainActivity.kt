package com.example.framgianguyentuananhe.movie.screen.home

import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.*
import android.view.View
import com.example.framgianguyentuananhe.movie.R
import com.example.framgianguyentuananhe.movie.data.model.Genre
import com.example.framgianguyentuananhe.movie.data.model.Movie
import com.example.framgianguyentuananhe.movie.data.repositories.GenreRepository
import com.example.framgianguyentuananhe.movie.data.repositories.MovieRepository
import com.example.framgianguyentuananhe.movie.data.source.remote.GenreRemoteDateSource
import com.example.framgianguyentuananhe.movie.data.source.remote.MovieRemoteDataSource
import com.example.framgianguyentuananhe.movie.data.source.remote.api.ServiceGenerator
import com.example.framgianguyentuananhe.movie.screen.base.BaseActivity
import com.example.framgianguyentuananhe.movie.screen.home.adapter.GenreAdapter
import com.example.framgianguyentuananhe.movie.screen.home.adapter.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_genre.*

class MainActivity : BaseActivity(), HomeContract.View {

    private var mPresenter: HomePresenter? = null
    var mGenreAdapter: GenreAdapter? = null
    var mMovieAdapter: MovieAdapter? = MovieAdapter()
    private var mBottomSheet: BottomSheetBehavior<ConstraintLayout>? = null

    override fun getContentLayout(): Int = R.layout.activity_main

    override fun initComponents() {
        val api = ServiceGenerator.getApi(this)
        val genreRemoteDateSource = GenreRemoteDateSource(api)
        val movieRemoteDataSource = MovieRemoteDataSource(api)
        mPresenter = HomePresenter(
            this,
            GenreRepository(genreRemoteDateSource),
            MovieRepository(movieRemoteDataSource)
        )
        mPresenter!!.getGenres()
        mPresenter!!.getMovies()
    }

    override fun initView() {
        mBottomSheet = BottomSheetBehavior.from(bottom_sheet_genre)
        mBottomSheet!!.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
            }

            override fun onStateChanged(p0: View, state: Int) {
                if (state == STATE_COLLAPSED) {
                    view_faded_in.visibility = View.GONE
                }
            }
        })
        button_genre.setOnClickListener {
            if (mBottomSheet!!.state != STATE_EXPANDED) {
                view_faded_in.visibility = View.VISIBLE
                mBottomSheet!!.state = STATE_EXPANDED
            }
        }
        view_faded_in.setOnClickListener { mBottomSheet!!.state = STATE_COLLAPSED }

        mGenreAdapter = GenreAdapter {
            mBottomSheet!!.state = STATE_COLLAPSED
            mPresenter!!.getMoviesByGenre(it!!, 1)
        }
        recycler_genre!!.adapter = mGenreAdapter
        recycler_movie!!.adapter = mMovieAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.mSubcription.clear()
    }

    override fun hideLoadingProgress() {
        progress_loading.visibility = View.GONE
    }

    override fun showLoadingProgress() {
        progress_loading.visibility = View.VISIBLE
    }

    override fun showGenre(genres: List<Genre.Data>?) {
        mGenreAdapter!!.addGenre(genres)
    }

    override fun showMovies(movies: List<Movie>?) {
        mMovieAdapter!!.addData(movies)
    }

    override fun onBackPressed() {
        recycler_movie.smoothScrollToPosition(0)
    }

}
