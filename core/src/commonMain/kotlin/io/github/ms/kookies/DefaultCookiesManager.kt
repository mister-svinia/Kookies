package io.github.ms.kookies

import com.mplata.cookies.MultipleCookiesResolutionStrategy
import io.github.ms.kookies.writeCookie as nativeWriteCookie
import io.github.ms.kookies.readAllCookies as nativeReadAllCookies
import io.github.ms.kookies.dropCookie as nativeDropCookie

class DefaultCookiesManager(
    override var multipleCookiesResolutionStrategy: MultipleCookiesResolutionStrategy = MultipleCookiesResolutionStrategy.FIRST,
) : CookiesManager {
    override fun writeCookie(
        key: String,
        value: String,
        path: String?,
        domain: CookieDomainProperty,
        expires: CookieExpiresProperty,
        isSecure: Boolean,
        samesite: CookieSamesiteProperty?,
    ) = nativeWriteCookie(
        key,
        buildString {
            append("$value;")
            path?.let { path ->
                append("path=$path;")
            }
            if (domain !is CookieDomainProperty.Current) {
                append("${domain.key}=${domain.value};")
            }
            if (expires !is CookieExpiresProperty.Session) {
                append("${expires.key}=${expires.value};")
            }
            if (!isSecure) {
                append("secure;")
            }
            samesite?.let { samesite ->
                append("${samesite.key}=${samesite.value};")
            }
        }
    )

    override fun readAllCookies(
        predicate: (key: String) -> Boolean,
    ): List<CookieData> = nativeReadAllCookies()
        .map {
            val rawValues = it.split("=", limit = 2)
            val (key, value) = when (rawValues.size) {
                0 -> "" to ""
                1 -> rawValues[0].trimStart(' ') to ""
                2 -> rawValues[0].trimStart(' ') to rawValues[1]

                else -> error("Unreachable!")
            }
            CookieData(
                key = key,
                value = value,
            )
        }
        .filter { (key, _) ->
            predicate(key)
        }

    override fun readCookieOrNull(
        key: String,
    ): CookieData? {
        return CookieData(
            key = key,
            value = readAllCookies { it == key }.unpackCookie() ?: return null
        )
    }

    override fun dropCookie(key: String) {
        nativeDropCookie(key)
    }
}
