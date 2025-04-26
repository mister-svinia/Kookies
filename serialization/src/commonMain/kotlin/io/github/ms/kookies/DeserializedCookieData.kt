package io.github.ms.kookies

data class DeserializedCookieData<T>(
    val key: String,
    val value: T,
)
