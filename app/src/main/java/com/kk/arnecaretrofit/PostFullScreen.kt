package com.kk.arnecaretrofit

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_full_screen.*

class PostFullScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_full_screen)

        val gelen = intent.getSerializableExtra("tiklanilanFotograf") as Post

        val url=gelen.media

        if(gelen.mediaType=="image"){
            imageViewPostFullScreen.isVisible=true
            videoViewPostVideoFulScreen.isVisible=false
            Picasso.get().load(url).into(imageViewPostFullScreen)
        }
        if (gelen.mediaType=="video"){
            imageViewPostFullScreen.isVisible=false
            videoViewPostVideoFulScreen.isVisible=true
            val videoUrl=Uri.parse("${url}")
            videoViewPostVideoFulScreen.setVideoURI(videoUrl)
            videoViewPostVideoFulScreen.start()


            videoViewPostVideoFulScreen.setOnPreparedListener { mediaPlayer ->
                val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
                val screenRatio = videoViewPostVideoFulScreen.width / videoViewPostVideoFulScreen.height.toFloat()
                val scaleX = videoRatio / screenRatio
                if (scaleX >= 1f) {
                    videoViewPostVideoFulScreen.scaleX = scaleX
                } else {
                    videoViewPostVideoFulScreen.scaleY = 1f / scaleX
                }
            }
        }




    }
}