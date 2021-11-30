package com.darkabhi.covidproject.home.data.network.repository

import com.darkabhi.covidproject.data.network.helper.safeApiCall
import com.darkabhi.covidproject.data.network.service.NewsApiService
import com.darkabhi.covidproject.home.news.models.NewsModel
import com.darkabhi.covidproject.models.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/1/2021.
 */
class NewsRepositoryImpl(
    private val newsApiService: NewsApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsRepository {
    override suspend fun getNews(
        country: String,
        category: String,
        apiKey: String
    ): ResultWrapper<NewsModel> {
        return safeApiCall(dispatcher) { newsApiService.getNews(country, category, apiKey) }
    }
}
