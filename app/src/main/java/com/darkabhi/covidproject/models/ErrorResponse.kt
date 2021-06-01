package com.darkabhi.covidproject.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/25/2021.
 */
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message")
    val error: String, // this is the translated error shown to the user directly from the API
)
