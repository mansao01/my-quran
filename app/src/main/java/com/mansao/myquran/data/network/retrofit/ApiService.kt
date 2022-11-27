package com.mansao.myquran.data.network.retrofit

import com.mansao.myquran.data.network.response.ResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("data")
    fun getQuranSurah(): Call<List<ResponseItem>>
}