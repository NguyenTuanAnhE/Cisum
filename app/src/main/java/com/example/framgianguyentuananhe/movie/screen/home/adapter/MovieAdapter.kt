package com.example.framgianguyentuananhe.movie.screen.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.framgianguyentuananhe.movie.R
import com.example.framgianguyentuananhe.movie.data.model.Movie
import com.example.framgianguyentuananhe.movie.utils.Constant
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var mMovies: MutableList<Movie>? = null
    var mInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.context);
        }
        return ViewHolder(mInflater!!.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mMovies == null || mMovies!!.isEmpty()) return
        holder.bindData(mMovies!![position])
    }

    fun addData(movies: List<Movie>?, mIsReload: Boolean) {
        if (mMovies == null) {
            mMovies = ArrayList()
        }
        if (mIsReload) mMovies!!.clear()
        mMovies!!.addAll(movies!!)
        notifyDataSetChanged()
    }

    fun clear() {
        mMovies!!.clear()
    }

    override fun getItemCount(): Int = mMovies?.size ?: 0

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindData(movie: Movie) {
            text_movie_title.text = movie.title ?: movie.originalName ?: movie.originalTitle ?: ""
            Glide.with(image_poster).load(Constant.IMAGE_URL + movie.posterPath).into(image_poster)
        }

    }
}
