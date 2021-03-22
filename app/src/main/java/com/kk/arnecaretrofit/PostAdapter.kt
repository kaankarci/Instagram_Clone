package com.kk.arnecaretrofit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Space
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostAdapter(private val mContext: Context, private val postListe:List<Post>): RecyclerView.Adapter<PostAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim) {

        var textViewKullaniciAdi: TextView
        var textViewLikeSayisi: TextView
        var textViewIzlenmeSayisi: TextView
        var textViewYorumSayisi: TextView
        var postThumbFoto: ImageView
        var postProfilFoto: ImageView
        var belirtecFoto: ImageView
        val post_card:View
        var begeniResim:ImageView
        var izlenmeResim:ImageView
        var yorumResim:ImageView
        var izlenmeBosluk:Space

      //  var storyFoto:ImageView

        init {
            textViewKullaniciAdi = tasarim.findViewById(R.id.textViewKullaniciAdi)
            textViewLikeSayisi = tasarim.findViewById(R.id.textViewLikeSayisi)
            textViewIzlenmeSayisi = tasarim.findViewById(R.id.textViewGoruntulenmeSayisi)
            textViewYorumSayisi = tasarim.findViewById(R.id.textViewYorumSayisi)
            postThumbFoto = tasarim.findViewById(R.id.imageViewPostFotografi)
            postProfilFoto = tasarim.findViewById(R.id.imageViewMiniProfilFotografi)
            belirtecFoto = tasarim.findViewById(R.id.belirtec)
            post_card=tasarim.findViewById(R.id.postCard)
            begeniResim=tasarim.findViewById(R.id.begeniRes)
            izlenmeResim=tasarim.findViewById(R.id.izlenmeRes)
            yorumResim=tasarim.findViewById(R.id.yorumRes)
            izlenmeBosluk=tasarim.findViewById(R.id.izlenmeBosluk)

      //  storyFoto=tasarim.findViewById(R.id.anasayfaStoryFotoRv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim= LayoutInflater.from(mContext).inflate(R.layout.post_card_tasarim,parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val post = postListe.get(position)
        if (post.media.takeLast(4) != ".mp4") {
            holder.belirtecFoto.isVisible = false
            holder.izlenmeResim.isVisible = false
            holder.textViewIzlenmeSayisi.isVisible = false
            holder.izlenmeBosluk.isVisible = false
        }
        else{
            holder.belirtecFoto.isVisible=true
            holder.izlenmeResim.isVisible=true
            holder.textViewIzlenmeSayisi.isVisible=true
            holder.izlenmeBosluk.isVisible=true
        }
        //kullanıcı adı
        holder.textViewKullaniciAdi.text = "${post.attendeeName}"

        //kucuk profıl fotografı
        if ("${post.attendeeProfileImg}" == "") {
            holder.postProfilFoto.setImageResource(R.drawable.ic_baseline_perm_identity_24)
        }
        else {
            Picasso.get()
                .load("https://v5.arneca.com${post.attendeeProfileImg}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postProfilFoto)
        }

        // thumbnail fotografları
        if ("${post.mediaThumb}" == "") {
            holder.postThumbFoto.setImageResource(R.drawable.fotoyok)
            if ("${post.media}".takeLast(3) != "mp4") {
                holder.belirtecFoto.isVisible = false
            post.media

            }
            else{                holder.belirtecFoto.isVisible = true
            }
        }
        else {
            Picasso.get()
                .load("${post.mediaThumb}")
                .resize(500,500).centerInside()
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postThumbFoto)


        }
        //like,izlenme,yorum sayısı
        holder.textViewYorumSayisi.text = "${post.commentCount}"
        holder.textViewLikeSayisi.text = "${post.likeCount}"
        holder.textViewIzlenmeSayisi.text = "${post.videoView}"

        holder.yorumResim.setOnClickListener {Toast.makeText(mContext, "Yorum sayısı:${post.commentCount}", Toast.LENGTH_SHORT).show() }
        holder.begeniResim.setOnClickListener {Toast.makeText(mContext, "Like sayısı:${post.likeCount}", Toast.LENGTH_SHORT).show() }
        holder.izlenmeResim.setOnClickListener {Toast.makeText(mContext, "Izlenme sayısı:${post.videoView}", Toast.LENGTH_SHORT).show() }
        //card'a tıklanınca profile gidecek

        holder.post_card.setOnClickListener {

            val tiklanilanPost=post
            val intent= Intent(mContext,ProfileActivity::class.java)
            intent.putExtra("tiklanilanPost",tiklanilanPost)
            mContext.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
return postListe.size
    }


}