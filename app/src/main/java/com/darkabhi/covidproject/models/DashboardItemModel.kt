package com.darkabhi.covidproject.models

import android.graphics.drawable.Drawable
import java.io.Serializable

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/28/2021.
 */
data class DashboardItemModel(
        val title: String,
        val description: String,
        val teamLead: String,
        val teamLeadContactNumber: Long,
        val image : Int
) : Serializable
