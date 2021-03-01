package com.kk.arnecaretrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CRUDCevap(
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("message")
    @Expose
    var message: String,
    @SerializedName("type")
    @Expose
    var type: String

    )

        {
}