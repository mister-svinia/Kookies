package io.github.ms.kookies

import com.mplata.cookies.MultipleCookiesResolutionStrategy
import kotlin.js.JsName

interface CookiesManager {
    var multipleCookiesResolutionStrategy: MultipleCookiesResolutionStrategy

    fun writeCookie(
        key: String,
        value: String,
        path: String? = null,
        domain: CookieDomainProperty = CookieDomainProperty.Current,
        expires: CookieExpiresProperty = CookieExpiresProperty.Session,
        isSecure: Boolean = false,
        samesite: CookieSamesiteProperty? = null,
    )

    fun readAllCookies(
        predicate: (key: String) -> Boolean = { _ -> true },
    ): List<CookieData>

    fun readCookieOrNull(
        key: String,
    ): CookieData?

    fun dropCookie(key: String)
}

@JsName("getCookiesManager")
fun CookiesManager(): CookiesManager {
    return DefaultCookiesManager()
}
