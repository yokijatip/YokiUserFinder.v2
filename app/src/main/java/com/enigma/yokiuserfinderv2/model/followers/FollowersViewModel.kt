package com.enigma.yokiuserfinderv2.model.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.data.remote.retrofit.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowersViewModel : ViewModel() {

    val followersList = MutableLiveData<ArrayList<UserData>>()
    val apiService = Client.apiInstance

    fun getFollower(username: String) {
        Client.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<UserData>> {
                override fun onResponse(
                    call: Call<ArrayList<UserData>>,
                    response: Response<ArrayList<UserData>>
                ) {
                    if (response.isSuccessful) {
                        followersList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserData>>, t: Throwable) {
                    t.message?.let { Log.d("Gagal", it) }
                }

            })
    }

    fun getListFollowers(): LiveData<ArrayList<UserData>> {
        return followersList
    }

}
