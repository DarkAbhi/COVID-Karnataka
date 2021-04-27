package com.darkabhi.covidproject.data.network.repository

import com.darkabhi.covidproject.data.network.service.CovidApiService
import com.darkabhi.covidproject.data.network.service.NewsApiService
import com.darkabhi.covidproject.models.CovidIndiaModel
import com.darkabhi.covidproject.models.CovidStateModelItem
import com.darkabhi.covidproject.models.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
class AppRepository(private val covidApiService: CovidApiService, private val newsApiService: NewsApiService) {
    suspend fun getIndiaData(): Response<CovidIndiaModel> = withContext(Dispatchers.IO) {
        return@withContext covidApiService.getIndiaData()
    }

    suspend fun getStateData(): Response<List<CovidStateModelItem>> = withContext(Dispatchers.IO) {
        return@withContext covidApiService.getStateDistrictData()
    }

    suspend fun getNews(country: String, category: String, apiKey: String): Response<NewsModel> = withContext(Dispatchers.IO) {
        return@withContext newsApiService.getNews(country, category, apiKey)
    }

}