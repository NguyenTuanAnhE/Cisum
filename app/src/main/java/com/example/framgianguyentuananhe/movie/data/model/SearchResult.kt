package com.example.framgianguyentuananhe.movie.data.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("page")
    val mPage: Int? = null,
    @SerializedName("total_results")
    val mTotalResults: Int? = null,
    @SerializedName("total_pages")
    val mTotalPages: Int? = null,
    @SerializedName("results")
    val mMovies: List<Movie>
)
