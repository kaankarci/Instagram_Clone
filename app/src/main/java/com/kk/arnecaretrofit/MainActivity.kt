package com.kk.arnecaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_card_tasarim.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter:PostAdapter
    private lateinit var pdi: PostsDaoInterface



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pdi = ApiUtils.getPostsDaoInterface()

        anasayfaRv.setHasFixedSize(true)
        anasayfaRv.layoutManager=LinearLayoutManager(this)
        tumPostlar()
        println("string".takeLast(3))
    }

    fun tumPostlar() {
        pdi.tumPostlar().enqueue(object : Callback<PostCevap> {

            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {

                if (response != null) {
                    val liste = response.body().post


                    adapter=PostAdapter(this@MainActivity,liste)
                    anasayfaRv.adapter=adapter


                }
                }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {

            }




        })

    }


}

