package com.enigma.yokiuserfinderv2.data.remote.response

class UserDetailData (

    val name : String,
    val login : String,
    val id : Int,
    val location: String,

//    Jumlah Followers & Following
    val followers : Int,
    val following : Int,

//    Url
    val avatar_url: String,
    val followers_url : String,
    val following_url : String
)