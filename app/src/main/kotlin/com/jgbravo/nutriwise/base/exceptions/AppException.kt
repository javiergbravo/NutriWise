package com.jgbravo.nutriwise.base.exceptions

abstract class AppException(
    val code: AppCodeException,
    val msg: String
) : Exception(msg)