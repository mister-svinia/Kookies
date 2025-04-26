package io.github.ms.kookies

import com.mplata.cookies.MultipleCookiesResolutionStrategy
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

private val localDateTimeFormatter by lazy {
    LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        char(',')
        char(' ')
        dayOfMonth(Padding.ZERO)
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        year(Padding.ZERO)
        char(' ')
        hour(Padding.ZERO)
        char(':')
        minute(Padding.ZERO)
        char(':')
        second(Padding.ZERO)
    }
}

internal fun LocalDateTime.toExpiresValueString(): String = format(localDateTimeFormatter)

context(cookiesManager: CookiesManager)
internal fun List<CookieData>.unpackCookie(): String? {
    return when (size) {
        0 -> null
        1 -> first().value
        else -> when (cookiesManager.multipleCookiesResolutionStrategy) {
            MultipleCookiesResolutionStrategy.THROW -> error("Multiple cookies specified for key!")
            MultipleCookiesResolutionStrategy.FIRST -> first().value
            MultipleCookiesResolutionStrategy.NULL -> null
        }
    }
}

internal expect fun getCurrentHostname(): String
