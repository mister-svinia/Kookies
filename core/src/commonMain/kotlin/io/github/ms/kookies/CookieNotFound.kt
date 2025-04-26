package io.github.ms.kookies

class CookieNotFound(key: String) : IllegalArgumentException("Cookie [$key] not found!")

@Suppress("NOTHING_TO_INLINE")
internal inline fun cookieNotFound(key: String): Nothing = throw CookieNotFound(key)
