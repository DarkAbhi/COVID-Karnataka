package com.darkabhi.covidproject.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "state_detail")
data class StateDetail(
    val state: String,
    @PrimaryKey
    val stateCode: String,
    val active: String,
    val confirmed: String,
    val deceased: String,
    val recovered: String
)
