package com.kk.arnecaretrofit

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.profile_post_card.*

class ProfileAdapter(private val mContext:Context,private val postListe:List<Post>):RecyclerView.Adapter<ProfileAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: View):RecyclerView.ViewHolder(tasarim){

        var textViewLikeSayisip: TextView
        var textViewIzlenmeSayisip: TextView
        var textViewYorumSayisip: TextView
        var textViewCommentp: TextView
        var postThumbFotop: ImageView
        val post_cardp:View
        var begeniResimp: ImageView
        var izlenmeResimp: ImageView
        var yorumResimp: ImageView
        var videop: VideoView
        init {
            textViewLikeSayisip = tasarim.findViewById(R.id.textViewLikeSayisip)
            textViewIzlenmeSayisip = tasarim.findViewById(R.id.textViewGoruntulenmeSayisip)
            textViewCommentp = tasarim.findViewById(R.id.textViewCommentp)
            textViewYorumSayisip = tasarim.findViewById(R.id.textViewYorumSayisip)
            postThumbFotop = tasarim.findViewById(R.id.imageViewPostFotografip)
            post_cardp=tasarim.findViewById(R.id.postCardp)
            begeniResimp=tasarim.findViewById(R.id.begeniResp)
            izlenmeResimp=tasarim.findViewById(R.id.izlenmeResp)
            yorumResimp=tasarim.findViewById(R.id.yorumResp)
            videop=tasarim.findViewById(R.id.videoViewPostVideop)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.profile_post_card,parent,false)

        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {


        val post = postListe.get(position)



        // media
        if ("${post.media}" == "") {
            holder.postThumbFotop.setImageResource(R.drawable.fotoyok)
            holder.videop.isVisible.not()
        }
        else {
            Picasso.get()
                .load("${post.media}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postThumbFotop)


        }
        if(post.media.takeLast(4)!=".mp4"){

            holder.postThumbFotop.isVisible.not()
            holder.videop.isVisible
        }

        //like,izlenme,yorum sayısı

        holder.textViewYorumSayisip.text = "${post.commentCount}"
        holder.textViewLikeSayisip.text = "${post.likeCount}"
        holder.textViewIzlenmeSayisip.text = "${post.videoView}"
        holder.textViewCommentp.text = "${post.comment}"
        holder.yorumResimp.setOnClickListener { Toast.makeText(mContext, "Yorum sayısı:${post.commentCount}", Toast.LENGTH_SHORT).show() }
        holder.begeniResimp.setOnClickListener { Toast.makeText(mContext, "Like sayısı:${post.likeCount}", Toast.LENGTH_SHORT).show() }
        holder.izlenmeResimp.setOnClickListener { Toast.makeText(mContext, "Izlenme sayısı:${post.videoView}", Toast.LENGTH_SHORT).show() }
        //card'a tıklanınca profile gidecek
        holder.post_cardp.setOnClickListener {

        }


    }

    override fun getItemCount(): Int {
    return postListe.size
    }


}


