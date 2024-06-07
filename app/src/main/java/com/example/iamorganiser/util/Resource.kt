package com.example.iamorganiser.util

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(val throwable: Throwable) : Resource<Nothing>()
}