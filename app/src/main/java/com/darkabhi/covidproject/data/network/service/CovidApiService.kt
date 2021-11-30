package com.darkabhi.covidproject.data.network.service

import com.darkabhi.covidproject.home.state.models.CovidIndiaModel
import com.darkabhi.covidproject.home.state.models.CovidStateModelItem
import retrofit2.http.GET

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/25/2021.
 */
interface CovidApiService {

    @GET("data.json")
    suspend fun getIndiaData(): CovidIndiaModel

    @GET("v2/state_district_wise.json")
    suspend fun getStateDistrictData(): List<CovidStateModelItem>
}
