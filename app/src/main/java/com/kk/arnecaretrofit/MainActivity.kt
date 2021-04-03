package com.kk.arnecaretrofit

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_card_tasarim.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PostAdapter
    private lateinit var pdi: PostsDaoInterface
    private  lateinit var adapterStory: MainStoryAdapter
    var soloStoryListe = ArrayList<Post>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

         //   val logoFont=ResourcesCompat.getFont(this,R.font.logo_font)
          //  textViewLogo.typeface=logoFont

        pdi = ApiUtils.getPostsDaoInterface()

        anasayfaRv.setHasFixedSize(true)
        anasayfaRv.layoutManager = LinearLayoutManager(this)

        anasayfaStoryFotoRv.setHasFixedSize(true)
        anasayfaStoryFotoRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        tumPostlar()
        tumStory()

    }

    fun tumPostlar() {
        pdi.tumPostlar().enqueue(object  : Callback<PostCevap>{
            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
                if (response!=null){
                    val liste= response.body().post


                    adapter= PostAdapter(this@MainActivity,liste)
                    anasayfaRv.adapter=adapter
                }
            }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {
            }

        })

    }
    fun tumStory() {

        pdi.tumPostlar().enqueue(object  : Callback<PostCevap>{
            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
                if (response!=null){
                    val liste= response.body().post
                    var tester=0
                    for(i in liste){
                        for(j in soloStoryListe){
                           if (i.attendeeName==j.attendeeName){tester=1}
                        }
                        if (tester==0){soloStoryListe.add(i)
                        println("solo isimler: "+soloStoryListe)}
                        tester=0
                    }

                    adapterStory= MainStoryAdapter(this@MainActivity,soloStoryListe)
                    anasayfaStoryFotoRv.adapter=adapterStory
                }
            }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {
            }

        })

    }


}

