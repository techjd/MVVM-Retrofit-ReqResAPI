package com.techjd.userlisttest.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.techjd.userlisttest.api.apiResponse
import com.techjd.userlisttest.api.reqresApi
import com.techjd.userlisttest.data.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel @ViewModelInject constructor(private val reqresApi: reqresApi) : ViewModel(){
    private var users: MutableLiveData<apiResponse>


    init {
        users = MutableLiveData<apiResponse>()
    }

    fun getUsers() : MutableLiveData<apiResponse> {
        makeCall()
        return users
    }

    fun makeCall() : MutableLiveData<apiResponse> {
        reqresApi.getUsers(2).enqueue(object : Callback<apiResponse> {
            override fun onResponse(
                call: Call<apiResponse>,
                response: Response<apiResponse>
            ) {
                users.postValue(response.body())
            }

            override fun onFailure(call: Call<apiResponse>, t: Throwable) {
                users.postValue(null)
            }

        })
        return users
    }
}