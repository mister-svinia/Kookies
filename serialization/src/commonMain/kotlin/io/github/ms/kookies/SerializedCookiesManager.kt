package io.github.ms.kookies

import kotlinx.serialization.StringFormat

class SerializedCookiesManager(
    private val cookiesManager: CookiesManager,
    private val serialFormat: StringFormat,
    var invalidSerializedCookieReadStrategy: InvalidSerializedCookieReadStrategy = InvalidSerializedCookieReadStrategy.NULL,
) : CookiesManager by cookiesManager, StringFormat by serialFormat

