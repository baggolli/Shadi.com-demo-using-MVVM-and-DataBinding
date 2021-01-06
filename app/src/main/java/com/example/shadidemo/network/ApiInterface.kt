package com.example.shadidemo.network

import com.example.shadidemo.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("?results=10")
    fun getUserResponse() : Call<UserResponse>
}