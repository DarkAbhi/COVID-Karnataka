package com.darkabhi.covidproject.home.data.network.repository

import com.darkabhi.covidproject.home.news.models.NewsModel
import com.darkabhi.covidproject.models.ResultWrapper

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/1/2021.
 */
interface NewsRepository {
    suspend fun getNews(country: String, category: String, apiKey: String): ResultWrapper<NewsModel>
}
