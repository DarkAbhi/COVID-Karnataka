package com.darkabhi.covidproject.data.network.service

import com.darkabhi.covidproject.covidresources.providers.models.ResourceModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/18/2021.
 */
interface ResourcesApiService {

    @GET("api/ambulance")
    suspend fun getAmbulanceResources(
        @Query("pageNo") pageNo: String,
        @Query("size") size: String
    ): Response<ResourceModel>

    @GET("api/blooddonor")
    suspend fun getBloodResources(
        @Query("pageNo") pageNo: String,
        @Query("size") size: String
    ): Response<ResourceModel>

    @GET("api/food")
    suspend fun getFoodResources(
        @Query("pageNo") pageNo: String,
        @Query("size") size: String
    ): Response<ResourceModel>

    @GET("api/hometesting")
    suspend fun getHomeTestingResources(
        @Query("pageNo") pageNo: String,
        @Query("size") size: String
    ): Response<ResourceModel>

    @GET("api/onlinedoc")
    suspend fun getOnlineDocResources(
        @Query("pageNo") pageNo: String,
        @Query("size") size: String
    ): Response<ResourceModel>

    @GET("api/oxygen")
    suspend fun getOxygenResources(
        @Query("pageNo") pageNo: String,
        @Query("size") size: String
    ): Response<ResourceModel>

    @GET("api/tele")
    suspend fun getTeleResources(
        @Query("pageNo") pageNo: String,
        @Query("size") size: String
    ): Response<ResourceModel>
}
