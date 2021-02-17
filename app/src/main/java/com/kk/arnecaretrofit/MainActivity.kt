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
       // postListele()
        successgoster()
    }

    fun postListele() {
        val kdi = ApiUtils.getPostsDaoInterface()
        kdi.tumPostlar().enqueue(object : Callback<PostCevap> {
            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
                if (response != null) {
                    val kisilerListe = response.body().post
                    for (k in kisilerListe) {
                        println("------" + "------")
                        println("kisi adı:" + k.attendeeName)
                    }
                }
            }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {

            }
        })

    }
    fun successgoster(){

        val kdi = ApiUtils.getPostsDaoInterface()
        kdi.tumCevap().enqueue(object :Callback<PostCevap>{
            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
                if (response !=null ){
                    val successListe = response.body().sucessMessage
                    for (k in successListe){
                        println(k.message)
                        println(k.title)
                        println(k.type)
                        println("*******")
                    }
                }
            }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {
                println("hata mesajı alındı")
            }
        })

    }

}

