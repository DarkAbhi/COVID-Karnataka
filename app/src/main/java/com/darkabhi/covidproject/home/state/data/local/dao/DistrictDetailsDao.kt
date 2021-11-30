package com.darkabhi.covidproject.home.state.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darkabhi.covidproject.data.room.models.DistrictDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface DistrictDetailsDao {

    @Query("SELECT * FROM district_detail where stateCode=:stateCode")
    fun getDistrictDetails(stateCode: String): Flow<List<DistrictDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistricts(districtList: List<DistrictDetail>)

    @Query("DELETE FROM district_detail")
    fun clearDistrictDetails()
}
