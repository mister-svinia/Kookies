package io.github.ms.kookies

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException

fun <T> SerializedCookiesManager.writeSerializedCookie(
    key: String,
    serializer: KSerializer<T>,
    value: T,
    path: String?,
    domain: CookieDomainProperty,
    expires: CookieExpiresProperty,
    isSecure: Boolean,
    samesite: CookieSamesiteProperty?,
) = writeCookie(
    key = key,
    value = encodeToString(serializer, value),
    path = path,
    domain = domain,
    expires = expires,
    isSecure = isSecure,
    samesite = samesite,
)

fun <T> SerializedCookiesManager.readSerializedAllCookies(
    serializer: KSerializer<T>,
    predicate: (String) -> Boolean,
): List<DeserializedCookieData<T>> {
    return readAllCookies(predicate)
        .map { cookieData ->
            DeserializedCookieData(
                key = cookieData.key,
                value = decodeFromString(serializer, cookieData.value)
            )
        }
}

fun <T> SerializedCookiesManager.readSerializedCookieOrNull(
    key: String,
    serializer: KSerializer<T>,
): DeserializedCookieData<T>? {
    return readCookieOrNull(key)?.let { rawCookie ->
        try {
            DeserializedCookieData(
                key = rawCookie.key,
                value = decodeFromString(serializer, rawCookie.value),
            )
        } catch (e: SerializationException) {
            when (invalidSerializedCookieReadStrategy) {
                InvalidSerializedCookieReadStrategy.THROW -> throw e
                InvalidSerializedCookieReadStrategy.NULL -> null
            }
        }
    }
}
