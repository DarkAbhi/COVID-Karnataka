package com.darkabhi.covidproject.home.data.network.repository

import androidx.room.withTransaction
import com.darkabhi.covidproject.data.network.helper.networkBoundResource
import com.darkabhi.covidproject.data.network.service.CovidApiService
import com.darkabhi.covidproject.data.room.CovidAppDatabase
import com.darkabhi.covidproject.home.state.data.local.dao.DistrictDetailsDao
import com.darkabhi.covidproject.home.state.data.local.dao.StateDetailDao
import com.darkabhi.covidproject.home.state.models.toDistrictDetail
import com.darkabhi.covidproject.home.state.models.toStateDetail
import javax.inject.Inject

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 6/1/2021.
 */
class CovidRepositoryImpl @Inject constructor(
    private val covidApiService: CovidApiService,
    private val districtDetailsDao: DistrictDetailsDao,
    private val stateDetailDao: StateDetailDao,
    private val covidAppDatabase: CovidAppDatabase,
) {

    fun getStateDetail(stateCode: String) = networkBoundResource(
        query = {
            stateDetailDao.getStateDetail(stateCode = stateCode)
        },
        fetch = {
            covidApiService.getIndiaData().statewise.map {
                it.toStateDetail()
            }
        },
        saveFetchResult = {
            covidAppDatabase.withTransaction {
                stateDetailDao.clearStateDetails()
                stateDetailDao.insertStates(it)
            }
        }
    )

    fun getDistrictDetails(stateCode: String) = networkBoundResource(
        query = {
            districtDetailsDao.getDistrictDetails(stateCode = stateCode)
        },
        fetch = {
            covidApiService.getStateDistrictData().flatMap {
                it.districtData.map { district ->
                    district.toDistrictDetail(it)
                }
            }
        },
        saveFetchResult = {
            covidAppDatabase.withTransaction {
                districtDetailsDao.clearDistrictDetails()
                districtDetailsDao.insertDistricts(it)
            }
        }
    )
}
