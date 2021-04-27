package com.darkabhi.covidproject.data.network.service

import com.darkabhi.covidproject.models.CovidIndiaModel
import com.darkabhi.covidproject.models.CovidStateModelItem
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
interface CovidApiService {

    @GET("data.json")
    suspend fun getIndiaData(): Response<CovidIndiaModel>

    @GET("v2/state_district_wise.json")
    suspend fun getStateDistrictData(): Response<List<CovidStateModelItem>>
}