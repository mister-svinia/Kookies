package io.github.ms.kookies

val Document.kookies: CookiesManager
    get() = RawCookiesManager
