pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "kookies"

include(
    ":core",
    ":serialization",
    ":escaping",
    ":raw",
)
