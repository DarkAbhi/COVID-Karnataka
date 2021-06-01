package com.darkabhi.covidproject.models

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/25/2021.
 */
sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}
