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

rootProject.name = "Kookies"

include(
    ":core",
    ":serialization",
    ":escaping",
    ":raw",
)
