package com.darkabhi.covidproject.home.data.network.repository

import com.darkabhi.covidproject.models.CovidIndiaModel
import com.darkabhi.covidproject.models.CovidStateModelItem
import com.darkabhi.covidproject.models.ResultWrapper

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/1/2021.
 */
interface CovidRepository {
    suspend fun getIndiaData(): ResultWrapper<CovidIndiaModel>
    suspend fun getStateData(): ResultWrapper<List<CovidStateModelItem>>
}
