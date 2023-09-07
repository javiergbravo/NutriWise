package com.jgbravo.nutriwise.common.exceptions.custom

import com.jgbravo.nutriwise.common.exceptions.base.AppException

data class MappingException(
    private val property: String,
    private val reason: MappingReason
) : AppException(
    msg = "Error mapping property '$property' cause $reason."
)

enum class MappingReason(val msg: String) {
    PROPERTY_NULL("Property is null or has different name"),
    PROPERTY_EMPTY("Property value is empty"),
    INVALID_NUMBER("Invalid number"),
    INVALID_ENUM("Invalid enum value"),
    INVALID_DATE("Invalid date"),
    PROPERTY_NOT_FOUND("Model has not this property"),
    BUILD_OBJECT("Error building object")
}