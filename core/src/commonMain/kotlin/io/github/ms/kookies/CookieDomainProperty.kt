package io.github.ms.kookies

sealed interface CookieDomainProperty : CookieProperty {
    override val key: String
        get() = "domain"

    data object Current : CookieDomainProperty {
        override val value = ""
    }

    data object CurrentSubdomains : CookieDomainProperty {
        override val value
            get() = getCurrentHostname()
    }

    value class Specified internal constructor(
        override val value: String,
    ) : CookieDomainProperty

    companion object {
        fun CookieDomainProperty(domain: String): CookieDomainProperty {
            return Specified(domain)
        }

        fun CookieDomainProperty(includeSubdomains: Boolean = false): CookieDomainProperty {
            return when (includeSubdomains) {
                true -> CurrentSubdomains
                false -> Current
            }
        }
    }
}
