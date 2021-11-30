package com.darkabhi.covidproject.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darkabhi.covidproject.data.room.models.DistrictDetail
import com.darkabhi.covidproject.data.room.models.StateDetail
import com.darkabhi.covidproject.home.state.data.local.dao.DistrictDetailsDao
import com.darkabhi.covidproject.home.state.data.local.dao.StateDetailDao

@Database(entities = [DistrictDetail::class, StateDetail::class], version = 1)
abstract class CovidAppDatabase : RoomDatabase() {
    abstract fun districtDetailsDao(): DistrictDetailsDao
    abstract fun stateDetailsDao(): StateDetailDao
}
