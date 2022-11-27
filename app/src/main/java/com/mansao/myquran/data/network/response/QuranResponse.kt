package com.mansao.myquran.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class QuranResponse(

    @field:SerializedName("Response")
    val response: List<ResponseItem>
)

@Parcelize
data class ResponseItem(

    @field:SerializedName("keterangan")
    val keterangan: String,

    @field:SerializedName("rukuk")
    val rukuk: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("ayat")
    val ayat: Int,

    @field:SerializedName("urut")
    val urut: String,

    @field:SerializedName("arti")
    val arti: String,

    @field:SerializedName("asma")
    val asma: String,

    @field:SerializedName("audio")
    val audio: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("nomor")
    val nomor: String
) : Parcelable
