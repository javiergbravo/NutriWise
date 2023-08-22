package com.jgbravo.nutriwise.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    private const val MIN_TWO_DIGIT = 10

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun LocalDate.minusMonths(months: Int): LocalDate {
        return this.minus(months, DateTimeUnit.MONTH)
    }

    fun LocalDate.plusMonths(days: Int): LocalDate {
        return this.plus(days, DateTimeUnit.MONTH)
    }

    fun LocalDate.formatDate(format: DatePatterns): String {
        val month = if (monthNumber < MIN_TWO_DIGIT) "0${monthNumber}" else monthNumber
        val day = if (dayOfMonth < MIN_TWO_DIGIT) "0${dayOfMonth}" else dayOfMonth
        val year = year
        return when (format) {
            DatePatterns.SPANISH_DATE_PATTERN -> "$day/$month/$year"
            DatePatterns.ENGLISH_DATE_PATTERN -> "$month/$day/$year"
        }
    }

    /**
     * Use it inside with try/catch
     */
    fun String.toLocalDate(format: DatePatterns): LocalDate {
        val dateString = this.split("/")
        val (day, month, year) = when (format) {
            DatePatterns.SPANISH_DATE_PATTERN -> {
                Triple(dateString[0].toInt(), dateString[1].toInt(), dateString[2].toInt())
            }
            DatePatterns.ENGLISH_DATE_PATTERN -> {
                Triple(dateString[1].toInt(), dateString[0].toInt(), dateString[2].toInt())
            }
        }
        return LocalDate(year, month, day)
    }
}