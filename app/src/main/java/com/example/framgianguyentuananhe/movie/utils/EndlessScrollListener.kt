package com.example.framgianguyentuananhe.movie.utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessScrollListener(val mGridLayoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {
    private var mFirstVisibleItem: Int = 0
    private var mVisibleItemCount: Int = 0
    private var mTotalItemCount: Int = 0
    private val mVisibleThreshold = 2
    private var mPreviousTotal = 0
    private var loading = true
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        mVisibleItemCount = recyclerView.childCount
        mTotalItemCount = mGridLayoutManager.getItemCount()
        mFirstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (mTotalItemCount > mPreviousTotal) {
                loading = false
                mPreviousTotal = mTotalItemCount
            }
        }

        if (!loading && mTotalItemCount - mVisibleItemCount <= mFirstVisibleItem + mVisibleThreshold) {
            currentPage++

            onLoadMore(currentPage)

            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)

    fun refresh() {
        currentPage = 1
        mPreviousTotal = 0
    }
}
