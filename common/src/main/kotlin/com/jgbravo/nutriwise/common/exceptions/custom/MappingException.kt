package com.jgbravo.nutriwise.common.exceptions.custom

import com.jgbravo.nutriwise.common.exceptions.base.AppException
import com.jgbravo.nutriwise.common.exceptions.codes.AppCodeException

data class MappingException(
    private val property: String,
    private val reason: MappingReason
) : AppException(
    code = AppCodeException.MAPPING_EXCEPTION,
    msg = "Error mapping property '$property' cause $reason."
)

enum class MappingReason(val msg: String) {
    PROPERTY_NOT_FOUND("Model has not this property"),
    PROPERTY_NULL("Property is null or has different name"),
    PROPERTY_EMPTY("Property value is empty"),
    BUILD_OBJECT("Error building object"),
    INVALID_NUMBER("Invalid number"),
    INVALID_ENUM("Invalid enum value")
}