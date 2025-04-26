package io.github.ms.kookies

sealed interface CookieProperty {
    val key: String
    val value: String
}
