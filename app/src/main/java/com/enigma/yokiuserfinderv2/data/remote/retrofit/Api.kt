package com.enigma.yokiuserfinderv2.data.remote.retrofit

import com.enigma.yokiuserfinderv2.data.remote.response.UserData
import com.enigma.yokiuserfinderv2.data.remote.response.UserDetailResponse
import com.enigma.yokiuserfinderv2.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

//  Token di set satu kali, ini saran dari Dicoding, yakali masih kena plagiat :P
    @Headers("Authorization: token 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")

//    Query user
    @GET("users")
    fun getUsers(): Call<ArrayList<UserData>>

//    Get Detail User
    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<UserDetailResponse>

//    Query User buat di search
    @GET("search/users")
    fun getUserSearch(
        @Query("q") query: String
    ): Call<UserResponse>

//    Get followers user
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UserData>>

//    Get following user
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<UserData>>

}