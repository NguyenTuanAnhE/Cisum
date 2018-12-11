package com.example.framgianguyentuananhe.movie.data.model

import com.google.gson.annotations.SerializedName

class ProductionCompany(

    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val country: String? = null
)