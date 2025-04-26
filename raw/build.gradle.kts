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

    sourceSets {
        commonMain {
            dependencies {
                api(project(":core"))
            }
        }
    }
}
