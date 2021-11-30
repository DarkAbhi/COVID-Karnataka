package com.darkabhi.covidproject.home.state.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darkabhi.covidproject.data.room.models.StateDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface StateDetailDao {

    @Query("SELECT * FROM state_detail where stateCode=:stateCode")
    fun getStateDetail(stateCode: String): Flow<StateDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStates(stateList: List<StateDetail>)

    @Query("DELETE FROM state_detail")
    fun clearStateDetails()
}
