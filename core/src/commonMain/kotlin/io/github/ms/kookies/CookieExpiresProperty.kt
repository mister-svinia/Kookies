package io.github.ms.kookies

import kotlinx.datetime.LocalDateTime
import kotlin.js.JsName
import kotlin.time.Duration

sealed interface CookieExpiresProperty : CookieProperty {
    data object Session : CookieExpiresProperty {
        override val key = ""
        override val value = ""
    }

    @ConsistentCopyVisibility
    data class MaxAge internal constructor(
        private val duration: Duration,
    ) : CookieExpiresProperty {
        override val key = "max-age"
        override val value
            get() = "${duration.inWholeSeconds}"
    }

    @ConsistentCopyVisibility
    data class ExpiresAt internal constructor(
        val timestamp: LocalDateTime,
    ) : CookieExpiresProperty {
        override val key = "expires"
        override val value
            get() = timestamp.toExpiresValueString()
    }

    companion object {
        @JsName("SessionCookieExpires")
        fun CookieExpiresProperty(): CookieExpiresProperty {
            return Session
        }

        fun CookieExpiresProperty(maxAge: Duration): CookieExpiresProperty {
            return MaxAge(maxAge)
        }

        fun CookieExpiresProperty(expiresAt: LocalDateTime): CookieExpiresProperty {
            return ExpiresAt(expiresAt)
        }
    }
}