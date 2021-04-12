package com.techjd.userlisttest.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techjd.userlisttest.data.Data
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface reqresApi {
    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }

    @GET("users")
    fun getUsers(
        @Query("page") query: Int
    ): Call<apiResponse>
}