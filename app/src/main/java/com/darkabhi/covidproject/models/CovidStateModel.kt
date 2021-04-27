package com.darkabhi.covidproject.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CovidStateModelItem(
        @Json(name = "districtData")
        val districtData: List<DistrictData>,
        @Json(name = "state")
        val state: String,
        @Json(name = "statecode")
        val statecode: String
)

@JsonClass(generateAdapter = true)
data class Delta(
        @Json(name = "confirmed")
        val confirmed: Int,
        @Json(name = "deceased")
        val deceased: Int,
        @Json(name = "recovered")
        val recovered: Int
)

@JsonClass(generateAdapter = true)
data class DistrictData(
        @Json(name = "active")
        val active: Int,
        @Json(name = "confirmed")
        val confirmed: Int,
        @Json(name = "deceased")
        val deceased: Int,
        @Json(name = "delta")
        val delta: Delta,
        @Json(name = "district")
        val district: String,
        @Json(name = "migratedother")
        val migratedother: Int,
        @Json(name = "notes")
        val notes: String,
        @Json(name = "recovered")
        val recovered: Int
)
