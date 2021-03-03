package com.kk.arnecaretrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostAdapter(private val mContext: Context, private val postListe:List<Post>)
    : RecyclerView.Adapter<PostAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim) {

        var post_card: CardView
        var textViewKullaniciAdi: TextView
        var textViewLikeSayisi: TextView
        var textViewIzlenmeSayisi: TextView
        var textViewYorumSayisi: TextView
        var postThumbFoto: ImageView
        var postProfilFoto: ImageView
        var belirtecFoto: ImageView

        init {
            textViewKullaniciAdi = tasarim.findViewById(R.id.textViewKullaniciAdiana)
            textViewLikeSayisi = tasarim.findViewById(R.id.textViewLikeSayisiana)
            textViewIzlenmeSayisi = tasarim.findViewById(R.id.textViewGoruntulenmeSayisiana)
            textViewYorumSayisi = tasarim.findViewById(R.id.textViewYorumSayisiana)
            postThumbFoto = tasarim.findViewById(R.id.imageViewPostFotografiana)
            postProfilFoto = tasarim.findViewById(R.id.imageViewMiniProfilFotografiana)
            belirtecFoto = tasarim.findViewById(R.id.belirtec)
            post_card = tasarim.findViewById(R.id.profileGoturenCard)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim =LayoutInflater.from(mContext).inflate(R.layout.post_card_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val post = postListe.get(position)
        if (post.media.takeLast(4) != ".mp4") {
            holder.belirtecFoto.isVisible = false
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
            }
        }
        else {
            Picasso.get()
                .load("${post.mediaThumb}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.postThumbFoto)


        }
        //like,izlenme,yorum sayısı
        holder.textViewYorumSayisi.text = "${post.commentCount}"
        holder.textViewLikeSayisi.text = "${post.likeCount}"
        holder.textViewYorumSayisi.text = "${post.videoView}"

        //card'a tıklanınca profile gidecek
        holder.post_card.setOnClickListener {
            val intent = Intent(mContext, ProfileActivity::class.java)
            intent.putExtra("postNesne",post)
            mContext.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return postListe.size
    }


}