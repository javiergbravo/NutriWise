package com.jgbravo.nutriwise.domain.base.models.wrappers

sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: Any?) : Resource<T>()

    data class Error(val exception: Exception) : Resource<Nothing>()
}