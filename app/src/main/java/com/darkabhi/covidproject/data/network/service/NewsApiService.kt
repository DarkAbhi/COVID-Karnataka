package com.darkabhi.covidproject.data.network.service

import com.darkabhi.covidproject.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/26/2021.
 */
interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getNews(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String): Response<NewsModel>
}