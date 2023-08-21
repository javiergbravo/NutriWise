package com.jgbravo.nutriwise.common.exceptions.base

import com.jgbravo.nutriwise.common.exceptions.codes.AppCodeException

abstract class AppException(
    val code: AppCodeException,
    val msg: String
) : Exception(msg)