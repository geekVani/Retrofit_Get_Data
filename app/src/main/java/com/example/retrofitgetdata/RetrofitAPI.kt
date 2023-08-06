package com.example.retrofitgetdata

import retrofit2.Call
import retrofit2.http.GET

/*
* Retrofit takes rest api and interfaces to kotlin code
* hence, create interface*/

interface RetrofitAPI {

    // call- to call retrofit library
    // get- crud = take and show data
    // post- last part of url to get annotation

    @GET("/posts")
    fun getAllPosts(): Call<List<Posts>>
}