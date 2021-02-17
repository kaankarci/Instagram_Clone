package com.kk.arnecaretrofit


class ApiUtils {
    companion object {
        val BASE_URL = "https://v5.arneca.com/api/"
        fun getPostsDaoInterface(): PostsDaoInterface {

            return RetrofitClient.getClient(BASE_URL)
                .create(PostsDaoInterface::class.java)
        }
    }
}