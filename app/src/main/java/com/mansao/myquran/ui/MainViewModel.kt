package com.mansao.myquran.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mansao.myquran.data.network.response.ResponseItem
import com.mansao.myquran.data.network.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listQuran = MutableLiveData<List<ResponseItem>>()
    val listQuran: LiveData<List<ResponseItem>> = _listQuran

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllSurah() {
        val client = ApiConfig.getApiService().getQuranSurah()
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    _listQuran.postValue(responseBody!!)
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}