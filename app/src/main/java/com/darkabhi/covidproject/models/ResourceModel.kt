package com.darkabhi.covidproject.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResourceModel(
    @Json(name = "data")
    val `data`: List<ResourceData>,
    @Json(name = "pages")
    val pages: Int,
    @Json(name = "size")
    val size: Int,
    @Json(name = "status")
    val status: String
)

@JsonClass(generateAdapter = true)
data class ResourceData(
    @Json(name = "available")
    val available: Boolean,
    @Json(name = "contact_email")
    val contactEmail: String,
    @Json(name = "contact_name")
    val contactName: String,
    @Json(name = "contact_number")
    val contactNumber: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "last_update_time")
    val lastUpdateTime: String,
    @Json(name = "link_to_go")
    val linkToGo: String,
    @Json(name = "location_covered")
    val locationCovered: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "source")
    val source: String,
    @Json(name = "timings")
    val timings: String,
    @Transient
    @Json(name = "__v")
    val v: Int = 0,
    @Json(name = "verified")
    val verified: String,
    @Json(name = "verified_by")
    val verifiedBy: String
)
