package com.darkabhi.covidproject.models

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/29/2021.
 */
data class UserStatus(
        val user_logged_in:Boolean
)
