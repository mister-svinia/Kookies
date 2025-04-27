package io.github.ms.kookies

import kotlinx.serialization.SerializationException

internal fun SerializedCookiesManager.resolveDecodingException(exception: SerializationException): Nothing? {
    return when (invalidSerializedCookieReadStrategy) {
        InvalidSerializedCookieReadStrategy.THROW -> throw exception
        InvalidSerializedCookieReadStrategy.NULL -> null
    }
}
