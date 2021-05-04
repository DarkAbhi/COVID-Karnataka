package com.darkabhi.covidproject.models

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.PropertyName
import com.google.firebase.ktx.Firebase
import java.io.Serializable
import java.util.*

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/28/2021.
 */
data class DetailsDataModel(
        @PropertyName("available")
        val available: Boolean? = null,
        @PropertyName("blood_group")
        val blood_group: String? = null,
        @PropertyName("capacity")
        val capacity: String? = null,
        @PropertyName("condition")
        val condition: Long? = null,
        @PropertyName("contact_email")
        val contact_email: String? = "",
        @PropertyName("contact_name")
        val contact_name: String? = "",
        @PropertyName("contact_number")
        val contact_number: String? = "",
        @PropertyName("covid_recovery_date")
        val covid_recovery_date: String? = null,
        @PropertyName("description")
        val description: String? = "",
        @PropertyName("id")
        val id: String? = "",
        @PropertyName("last_update_time")
        val last_update_time: Date? = null,
        @PropertyName("link_to_go")
        val link_to_go: String? = "",
        @PropertyName("location_covered")
        val location_covered: String? = "",
        @PropertyName("medicine_name")
        val medicine_name: String? = null,
        @PropertyName("more_numbers")
        val more_numbers: String? = null,
        @PropertyName("name")
        val name: String? = "",
        @PropertyName("price")
        val price: String? = null,
        @PropertyName("source")
        val source: String? = "",
        @PropertyName("timings")
        val timings: String? = "",
        @PropertyName("type")
        val type: Long? = null,
        @PropertyName("vaccinated")
        val vaccinated: Boolean? = null,
        @PropertyName("verified")
        val verified: Long? = 0L,
        @PropertyName("verified_by")
        val verified_by: String? = "",
        @PropertyName("verified_date")
        val verified_date: Date? = null,
        val user_logged_in: Boolean = Firebase.auth.currentUser != null
) : Serializable
