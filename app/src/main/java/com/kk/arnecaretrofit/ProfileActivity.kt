package com.kk.arnecaretrofit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.post_card_tasarim.*
import kotlinx.android.synthetic.main.profile_card_tasarim.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    private lateinit var pdi: PostsDaoInterface
    private lateinit var adapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        pdi = ApiUtils.getPostsDaoInterface()

        profileRv.setHasFixedSize(true)
        profileRv.layoutManager = LinearLayoutManager(this)

        profiliGetir()
        postlariGetir()
    }

    fun profiliGetir() {
        val gelenId = intent.getSerializableExtra("profilId")



        pdi.tumPostlar().enqueue(object : Callback<PostCevap> {
            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
                if (response != null) {

                    val liste = response.body().post
                    for (k in liste) {
                        if (k.attendeeId == gelenId) {
                            //Profil Fotografı
                            if (k.attendeeProfileImg == "") {
                                Picasso.get()
                                    .load(R.drawable.ic_baseline_perm_identity_24)
                                    .error(R.drawable.ic_baseline_perm_identity_24)
                                    .into(imageViewProfilFoto)
                            } else {
                                Picasso.get()
                                    .load("https://v5.arneca.com${k.attendeeProfileImg}")
                                    .error(R.drawable.ic_baseline_perm_identity_24)
                                    .into(imageViewProfilFoto)
                            }
                            //Kullanıcı Adı
                            textViewKullaniciAdiProfil.text = k.attendeeName
                            //Toplam statlar
                            var toplamgonderi = 0
                            var toplamBegeni = 0
                            var toplamIzlenme = 0
                            var toplamYorum = 0
                            for (j in liste) {
                                if (j.attendeeId == gelenId) {

                                    toplamYorum = toplamYorum + j.commentCount
                                    textViewIzlenmeSayisi.text = "${toplamYorum}\nYorum Sayısı"
                                    toplamIzlenme = toplamIzlenme + j.videoView
                                    textViewIzlenmeSayisi.text = "${toplamIzlenme}\nİzlenme Sayısı"
                                    toplamBegeni = toplamBegeni + j.likeCount
                                    textViewBegeniSayisi.text = "${toplamBegeni}\nBeğeni Sayısı"
                                    toplamgonderi++
                                    textViewGonderiSayisi.text = "${toplamgonderi}\nGönderi Sayısı"
                                }
                            }

                            break
                        }


                    }

                }
            }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {
            }


        })

        val intent2= Intent(this,ProfileAdapter::class.java)
        intent2.putExtra("profilId",gelenId)

    }

    fun postlariGetir() {
        pdi.tumPostlar().enqueue(object : Callback<PostCevap> {
            override fun onResponse(call: Call<PostCevap>?, response: Response<PostCevap>?) {
                if (response != null) {
                    val liste = response.body().post
                    adapter = ProfileAdapter(this@ProfileActivity, liste)
                    profileRv.adapter = adapter

                }
            }

            override fun onFailure(call: Call<PostCevap>?, t: Throwable?) {
            }

        })

    }
}
