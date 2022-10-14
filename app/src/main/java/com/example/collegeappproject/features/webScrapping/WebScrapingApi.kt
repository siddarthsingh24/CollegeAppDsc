package com.example.collegeappproject.features.webScrapping

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface WebScrapingApi {

    @GET
    suspend fun getNoticeData(@Url url :String) : Response<String>

    @GET
    suspend fun getNewsData(@Url url :String) : Response<String>
}