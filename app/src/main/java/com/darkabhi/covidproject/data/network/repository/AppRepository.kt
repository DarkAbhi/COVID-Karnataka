package com.darkabhi.covidproject.data.network.repository

import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.data.network.service.CovidApiService
import com.darkabhi.covidproject.data.network.service.NewsApiService
import com.darkabhi.covidproject.models.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
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

    @ExperimentalCoroutinesApi
    fun getAmbulanceServiceData(collectionName:String) = callbackFlow {
        // Emit loading state
        offer(State.Loading())
        val snapshots = Firebase.firestore
                .collection(collectionName)
        val subscription = snapshots.addSnapshotListener { value, error ->
            error?.let {
                offer(State.Failed(it.message.toString()))
                cancel(it.message.toString())
            }
            offer(State.Success(value?.toObjects(DetailsDataModel::class.java)))
        }
        awaitClose { subscription.remove() }
    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.Failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)


}