package com.darkabhi.covidproject.models

import java.io.Serializable

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/28/2021.
 */
data class DashboardItemModel(
    val title: String,
    val description: String,
    val image: Int
) : Serializable
