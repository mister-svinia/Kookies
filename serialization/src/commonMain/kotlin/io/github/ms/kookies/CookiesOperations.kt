package io.github.ms.kookies

import kotlinx.serialization.serializer

inline fun <reified T> SerializedCookiesManager.writeSerializedCookie(
    key: String,
    value: T,
    path: String?,
    domain: CookieDomainProperty,
    expires: CookieExpiresProperty,
    isSecure: Boolean,
    samesite: CookieSamesiteProperty?,
) = writeSerializedCookie(
    key = key,
    serializer = serializer(),
    value = value,
    path = path,
    domain = domain,
    expires = expires,
    isSecure = isSecure,
    samesite = samesite,
)

inline fun <reified T> SerializedCookiesManager.readSerializedAllCookies(
    noinline predicate: (String) -> Boolean,
): List<DeserializedCookieData<T>> = readSerializedAllCookies(serializer(), predicate)

inline fun <reified T> SerializedCookiesManager.readSerializedCookieOrNull(
    key: String,
): DeserializedCookieData<T>? = readSerializedCookieOrNull(key, serializer())
