package com.darkabhi.covidproject.home.state.models

import com.darkabhi.covidproject.data.room.models.DistrictDetail
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
data class DistrictData(
    @Json(name = "active")
    val active: Int,
    @Json(name = "confirmed")
    val confirmed: Int,
    @Json(name = "deceased")
    val deceased: Int,
    @Json(name = "district")
    val district: String,
    @Json(name = "recovered")
    val recovered: Int
)

fun DistrictData.toDistrictDetail(state: CovidStateModelItem): DistrictDetail {
    return DistrictDetail(
        state = state.state,
        stateCode = state.statecode,
        active = active,
        confirmed = confirmed,
        deceased = deceased,
        district = district,
        recovered = recovered
    )
}
