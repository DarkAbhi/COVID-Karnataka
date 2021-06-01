package com.darkabhi.covidproject.models

/**
 * Created by Abhishek AN on 3/1/2021.
 */
sealed class State {
    data class Success<T>(val data: T? = null) : State()
    data class Failed(val message: String?) : State()
    object Loading : State()
    object Empty : State()
}
