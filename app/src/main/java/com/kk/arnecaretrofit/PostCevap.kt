package com.kk.arnecaretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostCevap (
    @SerializedName ("result")
    @Expose
    var result:List<Post>,

    @SerializedName ("result_message")
    @Expose
    var resultMessage:List<CRUDCevap>


        ){
}