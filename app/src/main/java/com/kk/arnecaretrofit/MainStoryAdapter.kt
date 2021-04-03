package com.kk.arnecaretrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainStoryAdapter(private val mContext: Context, private val postListe:List<Post> ): RecyclerView.Adapter<MainStoryAdapter.CardTasarimTutucu>() {
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
        val tekler= arrayListOf<String>()
        val gelenler= arrayListOf<String>()
        var test=0

        post.attendeeId.toString().forEach { println(it) }



        for (i in post.attendeeId.toString()){
            for (j in gelenler){
                if (i.toString()==j){
                    test=1
                    break}
            }
            if (test!=1){
                tekler.add(i.toString())
                test=0}
        }




        for (k in tekler){
          //  println("teklerde gelenler: "+k)
        }

        //fotografları veriyor baslangıc
            Picasso.get()
                .load("https://v5.arneca.com${post.attendeeProfileImg}")
                .error(R.drawable.ic_baseline_perm_identity_24)
                .into(holder.imageViewStoryFoto)
        //fotografları verıyor bitiş

    }

    override fun getItemCount(): Int {
        return postListe.size
    }
}