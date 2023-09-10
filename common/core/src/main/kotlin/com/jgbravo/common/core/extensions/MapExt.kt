package com.jgbravo.common.core.extensions

import com.jgbravo.common.core.exceptions.custom.MappingException
import com.jgbravo.common.core.exceptions.custom.MappingReason.PROPERTY_NULL
import kotlin.reflect.KProperty0

/**
 * Get a property of a class or throw MappingException
 *  Example to use: model::property.getOrThrow()
 * @receiver KProperty0<T?> Property to get
 * @return T Value of property
 */
fun <T> KProperty0<T?>.getOrThrow(): T {
    return this.get() ?: throw MappingException(this.name, PROPERTY_NULL)
}

fun KProperty0<String>.getIfNotEmptyOrThrow(): String {
    return this.takeIf { it.get().isNotEmpty() }?.get() ?: throw MappingException(this.name, PROPERTY_NULL)
}

fun <T> KProperty0<List<T>>.getIfNotEmptyOrThrow(): List<T> {
    return this.takeIf { it.get().isNotEmpty() }?.get() ?: throw MappingException(this.name, PROPERTY_NULL)
}

fun KProperty0<Int>.getIfNotOrThrow(defaultValue: Int = -1): Int {
    return this.takeIf { it.get() != defaultValue }?.get() ?: throw MappingException(this.name, PROPERTY_NULL)
}