package io.github.ms.kookies

internal expect fun writeCookie(
    key: String,
    value: String,
)

internal expect fun readAllCookies(): List<String>

internal expect fun dropCookie(key: String)
