package io.github.ms.kookies

import kotlin.io.encoding.Base64

internal fun String.encodeBase64(): String {
    return Base64.encode(encodeToByteArray())
}

internal fun String.decodeBase64(): String {
    return Base64.decode(this).decodeToString()
}
