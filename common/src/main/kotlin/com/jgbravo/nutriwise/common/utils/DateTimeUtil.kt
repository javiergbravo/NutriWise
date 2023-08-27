package com.jgbravo.nutriwise.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    private const val MIN_TWO_DIGIT = 10
    private const val DATE_REGEX = "\\d{2}/[0-9]{2}/[0-9]{4}"

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun getLocalDateTimeFromMillis(millis: Long): LocalDateTime {
        return Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun String.toLocalDateTime(): LocalDateTime? = try {
        LocalDateTime.parse(this)
    } catch (e: IllegalArgumentException) {
        null
    }

    fun LocalDate.minusMonths(months: Int): LocalDate {
        return this.minus(months, DateTimeUnit.MONTH)
    }

    fun LocalDate.plusMonths(days: Int): LocalDate {
        return this.plus(days, DateTimeUnit.MONTH)
    }

    fun LocalDate.formatDate(format: DatePattern): String {
        val month = if (monthNumber < MIN_TWO_DIGIT) "0${monthNumber}" else monthNumber
        val day = if (dayOfMonth < MIN_TWO_DIGIT) "0${dayOfMonth}" else dayOfMonth
        val year = year
        return when (format) {
            DatePattern.SPANISH_DATE_PATTERN -> "$day/$month/$year"
            DatePattern.ENGLISH_DATE_PATTERN -> "$month/$day/$year"
        }
    }

    fun String.toLocalDate(format: DatePattern): LocalDate? {
        return if (Regex(DATE_REGEX).matches(this)) {
            val dateString = this.split("/")
            val (day, month, year) = when (format) {
                DatePattern.SPANISH_DATE_PATTERN -> {
                    Triple(dateString[0].toInt(), dateString[1].toInt(), dateString[2].toInt())
                }
                DatePattern.ENGLISH_DATE_PATTERN -> {
                    Triple(dateString[1].toInt(), dateString[0].toInt(), dateString[2].toInt())
                }
            }
            LocalDate(year, month, day)
        } else {
            null
        }
    }
}