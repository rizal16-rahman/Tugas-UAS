package com.aku.git.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aku.git.api.RetrofitClien
import com.aku.git.data.model.User
import com.aku.git.data.model.UserRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel :ViewModel(){
    val listUser = MutableLiveData<ArrayList<User>>()
    fun setSearchUser(query:String){
        RetrofitClien.apiInstance
            .getSearchUsers(query)
            .enqueue(object :Callback<UserRespon>{
                override fun onResponse(call: Call<UserRespon>, response: Response<UserRespon>) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                }}

                override fun onFailure(call: Call<UserRespon>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getSearchUsers():LiveData<ArrayList<User>>{
        return listUser
    }
}