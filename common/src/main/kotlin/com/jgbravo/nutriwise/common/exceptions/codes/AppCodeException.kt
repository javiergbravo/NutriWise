package com.jgbravo.nutriwise.common.exceptions.codes

import com.jgbravo.nutriwise.common.exceptions.codes.AppCodeException.UNKNOWN_EXCEPTION

enum class AppCodeException(val value: Int) {
    UNKNOWN_EXCEPTION(0),
    MAPPING_EXCEPTION(-1),
    API_CALL_EXCEPTION(-10),

    HTTP_BAD_REQUEST(400),
    HTTP_UNAUTHORIZED(401),
    HTTP_FORBIDDEN(403),
    HTTP_NOT_FOUND(404),
    HTTP_BAD_METHOD(405),
    HTTP_CLIENT_TIMEOUT(408),
    HTTP_LENGTH_REQUIRED(411),
    HTTP_UNSUPPORTED_TYPE(415),
    HTTP_SERVER_ERROR(500),
    HTTP_BAD_GATEWAY(502),
    HTTP_UNAVAILABLE(503)
}

fun Int.getAppCodeException(): AppCodeException {
    return AppCodeException.values().firstOrNull { it.value == this } ?: UNKNOWN_EXCEPTION
}