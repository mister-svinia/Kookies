package io.github.ms.kookies

import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

fun <T> SerializedCookiesManager.readRequiredSerializedCookie(
    key: String,
    serializer: KSerializer<T>,
): DeserializedCookieData<T> {
    val cookieData = readRequiredCookie(key)

    return DeserializedCookieData(
        key = cookieData.key,
        value = decodeFromString(serializer, cookieData.value)
    )
}

inline fun <reified T> SerializedCookiesManager.readRequiredSerializedCookie(
    key: String,
): DeserializedCookieData<T> = readRequiredSerializedCookie(key, serializer())
