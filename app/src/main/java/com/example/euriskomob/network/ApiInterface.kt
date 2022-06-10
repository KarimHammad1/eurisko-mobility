package com.example.euriskomob.network

import com.example.euriskomob.model.UserResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("todos")
    suspend fun getUserData():List<UserResponse>

}