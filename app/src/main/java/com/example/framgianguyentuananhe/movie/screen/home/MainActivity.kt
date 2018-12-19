package com.example.framgianguyentuananhe.movie.screen.home

import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.*
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.example.framgianguyentuananhe.movie.App
import com.example.framgianguyentuananhe.movie.R
import com.example.framgianguyentuananhe.movie.data.model.Genre
import com.example.framgianguyentuananhe.movie.data.model.Movie
import com.example.framgianguyentuananhe.movie.screen.base.BaseActivity
import com.example.framgianguyentuananhe.movie.screen.home.adapter.GenreAdapter
import com.example.framgianguyentuananhe.movie.screen.home.adapter.MovieAdapter
import com.example.framgianguyentuananhe.movie.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_genre.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HomeContract.View {

    @Inject
    lateinit var mPresenter: HomePresenter

    var mGenreAdapter: GenreAdapter? = null
    var mMovieAdapter: MovieAdapter? = MovieAdapter()
    lateinit var mLoadMore: EndlessScrollListener
    private var mBottomSheet: BottomSheetBehavior<ConstraintLayout>? = null
    var mGenre: String? = null

    override fun getContentLayout(): Int = R.layout.activity_main

    override fun initComponents() {
        App.mAppComponent?.inject(this)
        mPresenter.mView = this
        mPresenter.getGenres()
        mPresenter.getMovies()
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
        text_genre.setOnClickListener {
            if (mBottomSheet!!.state != STATE_EXPANDED) {
                view_faded_in.visibility = View.VISIBLE
                mBottomSheet!!.state = STATE_EXPANDED
            }
        }
        view_faded_in.setOnClickListener { mBottomSheet!!.state = STATE_COLLAPSED }

        mGenreAdapter = GenreAdapter {
            mBottomSheet!!.state = STATE_COLLAPSED
            mGenre = it
            if (text_genre.text.toString() != it) {
                mLoadMore.refresh()
                recycler_movie.smoothScrollToPosition(0)
                mPresenter.getMoviesByGenre(it!!, 1)
            }
        }
        recycler_genre!!.adapter = mGenreAdapter
        recycler_movie!!.adapter = mMovieAdapter
        mLoadMore =
                object : EndlessScrollListener(recycler_movie.layoutManager as GridLayoutManager) {
                    override fun onLoadMore(currentPage: Int) {
                        mPresenter.getMoviesByGenre(text_genre!!.text.toString(), currentPage)
                    }
                }
        recycler_movie.addOnScrollListener(mLoadMore)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.mSubcription.clear()
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
        mMovieAdapter!!.addData(movies, text_genre.text.toString() != mGenre)
        text_genre.text = mGenre
    }

    override fun onBackPressed() {
        recycler_movie.smoothScrollToPosition(0)
    }

}
