import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.maven.publish)
}

group = "io.github.mister-svinia.kookies"
version = libs.versions.it.get()

kotlin {
    withSourcesJar(true)

    js {
        browser()
        binaries.library()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.library()
    }

    sourceSets {
        commonMain {
            dependencies {
                api(project(":core"))
                api(libs.kotlinx.serialization)
            }
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "io.github.mister-svinia.kookies",
        artifactId = "serialization",
        version = libs.versions.it.get()
    )

    pom {
        name = "Kookies-serialization"
        description = """
            Js & Wasm/Js wrappers for work with cookie-files from Kotlin/Multiplatform.
            This artifact add support for type-safe serialization of cookies.
        """.trimIndent()
        url = "https://github.com/mister-svinia/Kookies"
        developers {
            developer {
                id = "n.savelev"
                name = "Niel Savelev"
                email = "i@savnil.ru"
            }
        }
    }
}