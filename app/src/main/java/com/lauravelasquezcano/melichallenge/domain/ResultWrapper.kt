package com.lauravelasquezcano.melichallenge.domain

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Failure<out T>(val exception: Exception) : ResultWrapper<T>()
    data class GenericError<out T>(val code : Int?, val message: String?) : ResultWrapper<T>()
}