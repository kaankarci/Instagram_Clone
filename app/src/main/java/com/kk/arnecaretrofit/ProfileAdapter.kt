package com.kk.arnecaretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(private val mContext:Context,private val postListe:List<Post>):RecyclerView.Adapter<ProfileAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: View):RecyclerView.ViewHolder(tasarim){
        var profilCard:CardView
        var textViewLikeSayisi: TextView
        var textViewIzlenmeSayisi: TextView
        var textViewYorumSayisi: TextView
        init {
            profilCard=tasarim.findViewById(R.id.profileCard)
            textViewIzlenmeSayisi=tasarim.findViewById(R.id.textViewGoruntulenmeSayisiana)
            textViewLikeSayisi=tasarim.findViewById(R.id.textViewLikeSayisiana)
            textViewYorumSayisi=tasarim.findViewById(R.id.textViewYorumSayisiana)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.profile_card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var post=postListe.get(position)
        holder.textViewYorumSayisi.text= post.commentCount.toString()
        holder.textViewLikeSayisi.text=post.likeCount.toString()
        holder.textViewIzlenmeSayisi.text=post.videoView.toString()
    }

    override fun getItemCount(): Int {
    return postListe.size
    }


}