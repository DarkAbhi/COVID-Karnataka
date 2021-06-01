package com.darkabhi.covidproject.home.data.network.repository

import com.darkabhi.covidproject.data.network.helper.safeApiCall
import com.darkabhi.covidproject.data.network.service.CovidApiService
import com.darkabhi.covidproject.models.CovidIndiaModel
import com.darkabhi.covidproject.models.CovidStateModelItem
import com.darkabhi.covidproject.models.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/1/2021.
 */
class CovidRepositoryImpl(
    private val covidApiService: CovidApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CovidRepository {
    override suspend fun getIndiaData(): ResultWrapper<CovidIndiaModel> {
        return safeApiCall(dispatcher) { covidApiService.getIndiaData() }
    }

    override suspend fun getStateData(): ResultWrapper<List<CovidStateModelItem>> {
        return safeApiCall(dispatcher) { covidApiService.getStateDistrictData() }
    }
}
