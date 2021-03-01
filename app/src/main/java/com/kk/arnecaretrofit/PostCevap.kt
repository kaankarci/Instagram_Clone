package com.kk.arnecaretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostCevap (
    @SerializedName ("result")
    @Expose
    var post:List<Post>,

    @SerializedName ("result_message")
    @Expose
    var sucessMessage:CRUDCevap


        ):Serializable
{
}