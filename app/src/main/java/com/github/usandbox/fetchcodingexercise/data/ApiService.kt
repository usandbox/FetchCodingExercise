package com.github.usandbox.fetchcodingexercise.data

import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}
