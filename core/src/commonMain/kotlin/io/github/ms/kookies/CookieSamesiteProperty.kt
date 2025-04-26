package io.github.ms.kookies

sealed interface CookieSamesiteProperty : CookieProperty {
    override val key: String
        get() = "samesite"

    data object Strict : CookieSamesiteProperty {
        override val value = "strict"
    }

    data object Lax : CookieSamesiteProperty {
        override val value = "lax"
    }
}