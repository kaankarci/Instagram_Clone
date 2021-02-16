package com.kk.arnecaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postListele()
    }

    fun postListele() {
val kdi = ApiUtils.getPostsDaoInterface()
kdi.tumPostlar().enqueue(object : Callback<PostCevap>{
    override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
        if (response!=null){
            val kisilerListe=response.body().result
            for (k in kisilerListe){
                Log.e("------","------")
                Log.e("kisi adı:", k.attendeeName)
            }
        }
    }

    override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {

    }
})

    }
        }

