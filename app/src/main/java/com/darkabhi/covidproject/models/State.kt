package com.darkabhi.covidproject.models

/**
 * Created by Abhishek AN on 3/1/2021.
 */
sealed class State<out T> {
    class Loading<out T> : State<T>()
    data class Success<out T>(val data: T) : State<T>()
    data class Failed<out T>(val message: String) : State<T>()
}
