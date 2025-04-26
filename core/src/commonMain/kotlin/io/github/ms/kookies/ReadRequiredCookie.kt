package io.github.ms.kookies

fun CookiesManager.readRequiredCookie(key: String): CookieData {
    return CookieData(
        key = key,
        value = readCookieOrNull(key)?.value ?: cookieNotFound(key),
    )
}
