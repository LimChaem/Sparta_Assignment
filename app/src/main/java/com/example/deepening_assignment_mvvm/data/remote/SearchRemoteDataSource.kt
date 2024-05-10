package com.example.deepening_assignment_mvvm.data.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchRemoteDataSource {
    @GET("/v2/search/image")
    suspend fun searchImage (
        @Query("query") searchKey: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80,
        @Header("Authorization") key: String = "KakaoAK 7d318f195e081597a201e85b7693fbe9"
    ) : SearchImageResponse
}