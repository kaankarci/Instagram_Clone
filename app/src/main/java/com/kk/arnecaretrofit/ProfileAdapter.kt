package com.kk.arnecaretrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProfileAdapter(private val mContext:Context,private val postListe:List<Post>):RecyclerView.Adapter<ProfileAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: View):RecyclerView.ViewHolder(tasarim){

        var textViewKullaniciAdip: TextView
        var textViewLikeSayisip: TextView
        var textViewIzlenmeSayisip: TextView
        var textViewYorumSayisip: TextView
        var postThumbFotop: ImageView
        var postProfilFotop: ImageView
        var belirtecFotop: ImageView
        val post_cardp:View
        var begeniResimp: ImageView
        var izlenmeResimp: ImageView
        var yorumResimp: ImageView
        init {
            textViewKullaniciAdip = tasarim.findViewById(R.id.textViewKullaniciAdip)
            textViewLikeSayisip = tasarim.findViewById(R.id.textViewLikeSayisip)
            textViewIzlenmeSayisip = tasarim.findViewById(R.id.textViewGoruntulenmeSayisip)
            textViewYorumSayisip = tasarim.findViewById(R.id.textViewYorumSayisip)
            postThumbFotop = tasarim.findViewById(R.id.imageViewPostFotografip)
            postProfilFotop = tasarim.findViewById(R.id.imageViewMiniProfilFotografip)
            belirtecFotop = tasarim.findViewById(R.id.belirtecp)
            post_cardp=tasarim.findViewById(R.id.postCardp)
            begeniResimp=tasarim.findViewById(R.id.begeniResp)
            izlenmeResimp=tasarim.findViewById(R.id.izlenmeResp)
            yorumResimp=tasarim.findViewById(R.id.yorumResp)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.profile_post_card,parent,false)

        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {


        val post = postListe.get(position)
        println("position degeri: "+ position)
        if (post.media.takeLast(4) != ".mp4") {
            holder.belirtecFotop.isVisible = false
        }
        //kullanıcı adı
        holder.textViewKullaniciAdip.text = "${post.attendeeName}"
        //kucuk profıl fotografı
        if ("${post.attendeeProfileImg}" == "") {
            holder.postProfilFotop.setImageResource(R.drawable.ic_baseline_perm_identity_24)

        }
        else {
            Picasso.get()
                .load("https://v5.arneca.com${post.attendeeProfileImg}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postProfilFotop)
        }
        // thumbnail fotografları
        if ("${post.mediaThumb}" == "") {
            holder.postThumbFotop.setImageResource(R.drawable.fotoyok)
            if ("${post.media}".takeLast(3) != "mp4") {
                holder.belirtecFotop.isVisible = false
            }
        }
        else {
            Picasso.get()
                .load("${post.mediaThumb}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postThumbFotop)


        }
        //like,izlenme,yorum sayısı
        holder.textViewYorumSayisip.text = "${post.commentCount}"
        holder.textViewLikeSayisip.text = "${post.likeCount}"
        holder.textViewIzlenmeSayisip.text = "${post.videoView}"
        holder.yorumResimp.setOnClickListener { Toast.makeText(mContext, "Yorum sayısı:${post.commentCount}", Toast.LENGTH_SHORT).show() }
        holder.begeniResimp.setOnClickListener { Toast.makeText(mContext, "Like sayısı:${post.likeCount}", Toast.LENGTH_SHORT).show() }
        holder.izlenmeResimp.setOnClickListener { Toast.makeText(mContext, "Izlenme sayısı:${post.videoView}", Toast.LENGTH_SHORT).show() }
        //card'a tıklanınca profile gidecek
        holder.post_cardp.setOnClickListener {
            /*  val intent = Intent(mContext, MainActivity::class.java)
              intent.putExtra("postNesne",post)
              mContext.startActivity(intent)*/




        }


    }

    override fun getItemCount(): Int {
    return postListe.size
    }


}