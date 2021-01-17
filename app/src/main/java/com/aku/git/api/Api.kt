package com.aku.git.api

import com.aku.git.data.model.DetailUser
import com.aku.git.data.model.User
import com.aku.git.data.model.UserRespon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/user")
    @Headers ("Autorization: token 472f3a5b23af63227a5b8efebcccdd72878d69b0")
    fun getSearchUsers(
        @Query("q") query : String
    ):Call<UserRespon>

    @GET("users/username")
    @Headers("Autorization: token 472f3a5b23af63227a5b8efebcccdd72878d69b0")
    fun getUserDetail(
            @Path("username") username: () -> String?
    ):Call<DetailUser>

    @GET("/users/{username}/followers")
    @Headers("Autorization: token 472f3a5b23af63227a5b8efebcccdd72878d69b0")
    fun getFollowers(
            @Path("username") username:String
    ):Call<ArrayList<User>>
    @GET("/users/{username}/following")
    @Headers("Autorization: token 472f3a5b23af63227a5b8efebcccdd72878d69b0")
    fun getFollowing(
            @Path("username") username:String
    ):Call<ArrayList<User>>
}