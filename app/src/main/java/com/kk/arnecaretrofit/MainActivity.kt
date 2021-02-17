package com.kk.arnecaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var kdi: PostsDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tumPostlar()
    }

    fun tumPostlar() {
        val kdi = ApiUtils.getPostsDaoInterface()
        kdi.tumPostlar().enqueue(object : Callback<PostCevap> {
            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
                if (response != null) {
                    var liste = response.body().post

                    for (goster in liste){
                        println(goster.id.toString())
                    }

                }

            }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {

            }
        })

    }


}

