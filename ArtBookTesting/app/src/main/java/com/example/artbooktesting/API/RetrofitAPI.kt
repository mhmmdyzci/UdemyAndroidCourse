package com.example.artbooktesting.API

import com.example.artbooktesting.Util.Util.API_KEY
import com.example.artbooktesting.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET(value = "/api/")
    suspend fun  imageSearch(
        @Query("q") searchQuery : String,
        @Query("key") apiKey : String = API_KEY

    ) : Response<ImageResponse>
}