package com.kk.arnecaretrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface PostsDaoInterface {

    @GET("PostWall?event_id=2&increment=20")
    fun tumPostlar(): Call<PostCevap>


}