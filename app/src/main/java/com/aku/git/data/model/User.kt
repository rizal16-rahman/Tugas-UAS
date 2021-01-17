package com.aku.git.data.model

data class User(
    val login:String,
    val avatar:String,
    val htmlUrl:String,
    val followerUrl:String,
    val followers:Int,
    val followingUrl:String,
    val following:Int,
    val repostUrl:String,
    val repost:Int
)
