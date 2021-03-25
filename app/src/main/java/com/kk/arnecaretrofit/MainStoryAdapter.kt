package com.kk.arnecaretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainStoryAdapter(private val mContext: Context, private val postListe:List<Post>): RecyclerView.Adapter<MainStoryAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim){
        var imageViewStoryFoto: ImageView

        init {
            imageViewStoryFoto=tasarim.findViewById(R.id.imageViewStoryFoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MainStoryAdapter.CardTasarimTutucu {
        val tasarim= LayoutInflater.from(mContext).inflate(R.layout.anasayfa_story_card_tasarim,parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: MainStoryAdapter.CardTasarimTutucu, position: Int) {
        val post=postListe.get(position)

        if ("${post.attendeeProfileImg}" == "") {
            holder.imageViewStoryFoto.setImageResource(R.drawable.ic_baseline_perm_identity_24)
        }
        else {
            Picasso.get()
                .load("https://v5.arneca.com${post.attendeeProfileImg}")
                .error(R.drawable.ic_baseline_share_24)
                .into(holder.imageViewStoryFoto)
        }


    }

    override fun getItemCount(): Int {
        return postListe.size
    }
}