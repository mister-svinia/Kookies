package io.github.ms.kookies

import kotlinx.browser.window

internal actual fun getCurrentHostname(): String {
    return window.location.hostname
}
