package com.enigma.yokiuserfinderv2.model.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.data.remote.response.UserResponse
import com.enigma.yokiuserfinderv2.data.remote.retrofit.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    private val userListLiveData = MutableLiveData<ArrayList<UserData>>()

    fun setSearchUsers(query: String) {
        Client.apiInstance
            .getUserSearch(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        userListLiveData.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, logging: Throwable) {
                    // nampilin pesan kalau error
                    logging.message?.let { Log.e("Mana User nya juga ga ada🫵😞", it) }
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<UserData>> {
        return userListLiveData
    }

}