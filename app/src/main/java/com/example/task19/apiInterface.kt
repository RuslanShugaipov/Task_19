package com.example.task19

import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {
    @GET("posts")
    fun getData(): Call<List<MsgItem>>
}