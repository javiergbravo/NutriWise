package com.jgbravo.nutriwise.common.exceptions.custom

import com.jgbravo.nutriwise.common.exceptions.base.AppException
import com.jgbravo.nutriwise.common.exceptions.custom.ApiCallReason.UNKNOWN

data class ApiCallException(private val reason: String?) : AppException(msg = reason ?: UNKNOWN.msg)

enum class ApiCallReason(val msg: String) {
    UNKNOWN("Unknown error from api response"),
    NULL_OBJECT("Null object from api response"),
    EMPTY_BODY("Empty body from api response")
}