package com.jgbravo.nutriwise.base.models.wrappers

import com.jgbravo.nutriwise.base.exceptions.AppCodeException

sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: Any?) : Resource<T>()

    data class Error(
        val code: Int = AppCodeException.UNKNOWN_EXCEPTION.value,
        val exception: Exception
    ) : Resource<Nothing>()
}