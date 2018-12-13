package com.example.framgianguyentuananhe.movie.screen.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.framgianguyentuananhe.movie.R
import com.example.framgianguyentuananhe.movie.data.model.Genre
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_genre.*

class GenreAdapter(private val mListener: (String?) -> Unit) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    var mInflater: LayoutInflater? = null
    var mGenres: MutableList<Genre.Data>? = null


    override fun onCreateViewHolder(viewHolder: ViewGroup, p1: Int): ViewHolder {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewHolder.context)
        }
        return ViewHolder(
            mInflater!!.inflate(
                R.layout.item_genre,
                viewHolder,
                false
            ), mListener
        )
    }

    override fun getItemCount(): Int {
        return if (mGenres == null) 0 else mGenres!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mGenres == null || mGenres!!.isEmpty()) return
        holder.bindData(mGenres!![position])
    }

    fun setGenre(genres: List<Genre.Data>?) {
        if (mGenres == null) {
            mGenres = ArrayList()
        } else {
            mGenres!!.clear()
        }
        mGenres!!.addAll(genres!!)
        notifyDataSetChanged()
    }

    fun addGenre(genres: List<Genre.Data>?) {
        if (mGenres == null) {
            mGenres = ArrayList()
        }
        mGenres!!.addAll(genres!!)
        notifyDataSetChanged()
    }

    class ViewHolder(override val containerView: View, val mListener: (String?) -> Unit) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindData(genre: Genre.Data) {
            text_genre.text = genre.mName
            text_genre.setOnClickListener { mListener(genre.mName) }
        }
    }

}
