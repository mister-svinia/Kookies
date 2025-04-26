package io.github.ms.kookies

import kotlinx.browser.document

internal actual fun writeCookie(key: String, value: String) {
    document.cookie = "$key=$value"
}

internal actual fun readAllCookies(): List<String> {
    return document.cookie.split(";")
}

internal actual fun dropCookie(key: String) {
    document.cookie = "$key=;max-age=-1;"
}
