package com.enigma.yokiuserfinderv2.model.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.data.remote.response.UserResponse
import com.enigma.yokiuserfinderv2.data.remote.retrofit.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val userListLiveData = MutableLiveData<List<UserData>>()
    private val apiService = Client.apiInstance

//    Constructor
    init {
        getUsers()
    }

//    Untuk ngambil data pengguna dari API
private fun getUsers() {
    Client.apiInstance
        .getUsers()
        .enqueue(object : Callback<ArrayList<UserData>> {
            override fun onResponse(
                call: Call<ArrayList<UserData>>,
                response: Response<ArrayList<UserData>>
            ) {
                if (response.isSuccessful) {
                    userListLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<UserData>>, logging: Throwable) {
                logging.message?.let { Log.e("Gagal🫵😞", it) }
            }
        })
}
    fun getUserListLiveData() : MutableLiveData<List<UserData>> {
        return userListLiveData
    }

}
