package com.enigma.yokiuserfinderv2.model.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.data.remote.response.UserDetailData
import com.enigma.yokiuserfinderv2.data.remote.response.UserDetailResponse
import com.enigma.yokiuserfinderv2.data.remote.retrofit.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val userDetailListLiveData = MutableLiveData<UserDetailResponse>()
    private val apiService = Client.apiInstance


    fun getUserDetail(username : String) {
        Client.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<UserDetailResponse> {
                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    response: Response<UserDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        userDetailListLiveData.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<UserDetailResponse>, logging: Throwable) {
                    logging.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun fetchUserDetail() : LiveData<UserDetailResponse> {
        return userDetailListLiveData
    }

}