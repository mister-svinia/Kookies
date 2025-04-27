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

    compilerOptions {
        freeCompilerArgs.apply {
            add("-Xcontext-parameters")
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlinx.datetime)
            }
        }
        wasmJsMain {
            dependencies {
                api(libs.kotlinx.browser)
            }
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "io.github.mister-svinia.kookies",
        artifactId = "core",
        version = libs.versions.it.get()
    )

    pom {
        name = "Kookies-core"
        description = """
            Js & Wasm/Js wrappers for work with cookie-files from Kotlin/Multiplatform.
        """.trimIndent()
        url = "https://github.com/mister-svinia/Kookies"

        scm {
            connection = "scm:https://github.com/mister-svinia/kookies.git"
            developerConnection = "scm:git@github.com:mister-svinia/kookies.git"
            url = "https://github.com/mister-svinia/kookies"
        }

        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }

        developers {
            developer {
                id = "MrSvinia"
                name = "Niel Savelev"
                email = "i@savnil.ru"
            }
        }
    }
}
