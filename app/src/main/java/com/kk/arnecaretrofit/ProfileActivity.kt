package com.kk.arnecaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.profile_card_tasarim.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val profil=intent.getSerializableExtra("postNesne") as Post

        textViewGoruntulenmeSayisiana.text=profil.videoView.toString()
        textViewYorumSayisiana.text=profil.commentCount.toString()
    }
}