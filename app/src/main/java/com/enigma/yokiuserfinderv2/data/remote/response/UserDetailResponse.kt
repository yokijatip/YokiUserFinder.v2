package com.enigma.yokiuserfinderv2.data.remote.response

data class UserDetailResponse (
    val id: Int,

    val avatar_url: String,
    val login: String?,
    val name: String?,
    val location: String?,

    val followers_url : String?,
    val following_url : String?,

    val followers : Int?,
    val following : Int?
)