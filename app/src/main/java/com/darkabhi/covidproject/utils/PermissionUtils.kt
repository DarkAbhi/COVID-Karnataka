package com.darkabhi.covidproject.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 4/29/2021.
 */
object PermissionUtils {
    fun Context.checkPhonePermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }
}