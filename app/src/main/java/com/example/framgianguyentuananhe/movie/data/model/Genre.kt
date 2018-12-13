package com.example.framgianguyentuananhe.movie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(
        @SerializedName("genres")
        @Expose
        var mDatas: List<Data>? = null
) {
    data class Data(
            @SerializedName("id")
            @Expose
            var mId: Int? = null,
            @SerializedName("name")
            @Expose
            var mName: String? = null
    )
}