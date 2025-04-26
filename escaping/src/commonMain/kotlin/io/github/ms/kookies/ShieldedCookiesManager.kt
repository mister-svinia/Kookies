package io.github.ms.kookies

import net.thauvin.erik.urlencoder.UrlEncoderUtil

class ShieldedCookiesManager(
    private val cookiesManager: CookiesManager,
    var filterStrategy: ShieldedFilterStrategy = ShieldedFilterStrategy.UNESCAPE_AND_FILTER,
    var escapeCookieKey: (String) -> String = UrlEncoderUtil::encode,
    var unescapeCookieKey: (String) -> String = UrlEncoderUtil::decode,
    var escapeCookieValue: (String) -> String = UrlEncoderUtil::encode,
    var unescapeCookieValue: (String) -> String = UrlEncoderUtil::decode,
) : CookiesManager by cookiesManager {
    override fun writeCookie(
        key: String,
        value: String,
        path: String?,
        domain: CookieDomainProperty,
        expires: CookieExpiresProperty,
        isSecure: Boolean,
        samesite: CookieSamesiteProperty?,
    ) = cookiesManager.writeCookie(
        key = escapeCookieKey(key),
        value = escapeCookieValue(value),
        path = path,
        domain = domain,
        expires = expires,
        isSecure = isSecure,
        samesite = samesite,
    )

    override fun readAllCookies(
        predicate: (String) -> Boolean,
    ): List<CookieData> {
        val cookies = cookiesManager.readAllCookies()

        return when (filterStrategy) {
            ShieldedFilterStrategy.FILTER_AND_UNESCAPE -> cookies.filterByKey(predicate).mapUnescape()
            ShieldedFilterStrategy.UNESCAPE_AND_FILTER -> cookies.mapUnescape().filterByKey(predicate)
        }
    }

    override fun readCookieOrNull(key: String): CookieData? {
        val escapedKey = escapeCookieKey(key)

        return CookieData(
            key = escapedKey,
            value = cookiesManager.readCookieOrNull(escapedKey)?.value?.let(unescapeCookieValue) ?: return null
        )
    }

    override fun dropCookie(key: String) {
        cookiesManager.dropCookie(escapeCookieKey(key))
    }

    private fun List<CookieData>.mapUnescape() = map { cookieData ->
        CookieData(
            key = unescapeCookieKey(cookieData.key),
            value = unescapeCookieValue(cookieData.value),
        )
    }

    private fun List<CookieData>.filterByKey(predicate: (String) -> Boolean) = filter { cookieData ->
        predicate(cookieData.key)
    }
}
