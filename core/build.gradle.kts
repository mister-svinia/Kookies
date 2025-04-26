import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.maven.publish)
}

group = "io.github.mister-svinia.kookies"
version = libs.versions.it.get()

kotlin {
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
