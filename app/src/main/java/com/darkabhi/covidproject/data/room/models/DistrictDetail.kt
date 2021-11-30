package com.darkabhi.covidproject.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "district_detail")
data class DistrictDetail(
    val state: String,
    val stateCode: String,
    val active: Int,
    val confirmed: Int,
    val deceased: Int,
    @PrimaryKey
    val district: String,
    val recovered: Int
)
