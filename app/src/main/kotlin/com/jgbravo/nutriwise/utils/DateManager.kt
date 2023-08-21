package com.jgbravo.nutriwise.utils

import java.time.LocalDateTime

object DateManager {

    const val DEFAULT_DATE_FORMAT = "dd/MM/yyyy"

    fun formatDefault(localDateTime: LocalDateTime): String {
        return localDateTime.format(java.time.format.DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT))
    }
}