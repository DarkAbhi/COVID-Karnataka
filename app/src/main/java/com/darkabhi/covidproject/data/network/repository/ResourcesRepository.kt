package com.darkabhi.covidproject.data.network.repository

import com.darkabhi.covidproject.data.network.service.ResourcesApiService
import com.darkabhi.covidproject.models.ResourceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/18/2021.
 */
class ResourcesRepository(
    private val resourcesApiService: ResourcesApiService
) {
    suspend fun getAmbulanceData(pageNo: String): Response<ResourceModel> =
        withContext(Dispatchers.IO) {
            return@withContext resourcesApiService.getAmbulanceResources(pageNo, "16")
        }

    suspend fun getBloodData(pageNo: String): Response<ResourceModel> =
        withContext(Dispatchers.IO) {
            return@withContext resourcesApiService.getBloodResources(pageNo, "16")
        }

    suspend fun getFoodData(pageNo: String): Response<ResourceModel> =
        withContext(Dispatchers.IO) {
            return@withContext resourcesApiService.getFoodResources(pageNo, "16")
        }

    suspend fun getHTData(pageNo: String): Response<ResourceModel> =
        withContext(Dispatchers.IO) {
            return@withContext resourcesApiService.getHomeTestingResources(pageNo, "16")
        }

    suspend fun getOnlineData(pageNo: String): Response<ResourceModel> =
        withContext(Dispatchers.IO) {
            return@withContext resourcesApiService.getOnlineDocResources(pageNo, "16")
        }

    suspend fun getOxygenData(pageNo: String): Response<ResourceModel> =
        withContext(Dispatchers.IO) {
            return@withContext resourcesApiService.getOxygenResources(pageNo, "16")
        }

    suspend fun getTeleData(pageNo: String): Response<ResourceModel> =
        withContext(Dispatchers.IO) {
            return@withContext resourcesApiService.getTeleResources(pageNo, "16")
        }
}
