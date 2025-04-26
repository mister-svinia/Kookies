package io.github.ms.kookies

import kotlinx.browser.window

actual fun getCurrentHostname(): String {
    return window.location.hostname
}
