package com.jgbravo.common.core.exceptions.custom

import com.jgbravo.common.core.exceptions.AppException
import com.jgbravo.common.core.exceptions.custom.ApiCallReason.UNKNOWN

data class ApiCallException(private val reason: String?) : AppException(msg = reason ?: UNKNOWN.msg)

enum class ApiCallReason(val msg: String) {
    UNKNOWN("Unknown error from api response"),
    NULL_OBJECT("Null object from api response"),
    EMPTY_BODY("Empty body from api response")
}