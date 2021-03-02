package com.anlyn.data.db.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private var retrofit:Retrofit? = null
    private var gson = GsonBuilder().setLenient().create()
    fun create():PhraseApi{
        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava3CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create(gson)
                )
                .baseUrl("https://anlyn.cafe24.com/")
                .build()
        }
        return retrofit!!.create(PhraseApi::class.java)
    }
}