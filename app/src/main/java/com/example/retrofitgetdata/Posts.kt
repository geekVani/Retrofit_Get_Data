package com.example.retrofitgetdata

import com.google.gson.annotations.SerializedName

data class Posts(
    val userId: Int,
    val id: Int,
    val title: String,

    // annotate data with , if using a different name than data
    // otherwise retrofit won't recognize the data
    @SerializedName("body")
    val subtitle: String) {
}