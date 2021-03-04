package com.kk.arnecaretrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface PostsDaoInterface {

    @GET("PostWall?attendee_id=228809199&increment=20&event_id=5")
    fun tumPostlar(): Call<PostCevap>



}