package com.aku.git.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aku.git.api.RetrofitClien
import com.aku.git.data.model.DetailUser
import retrofit2.Call
import retrofit2.Response

class DetailUserVm:ViewModel() {
    val user=MutableLiveData<DetailUser>()

    fun setUserDetail(username: () -> String?){
        RetrofitClien.apiInstance
            .getUserDetail(username)
            .enqueue(object : retrofit2.Callback<DetailUser> {
                override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>)
                {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }
    fun getUserDetail(): LiveData<DetailUser>{
        return user
    }
}