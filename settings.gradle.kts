rootProject.name = "Mehmaan"
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("plugin.serialization").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        id("com.android.base").version(agpVersion)
        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("com.google.firebase.crashlytics").version("2.9.5")
        id("org.jetbrains.compose").version(composeVersion)
    }
}

fun include(name: String, path: String? = null) {
    val newName = ":$name"
    settings.include(newName)
    if (path != null) {
        project(newName).projectDir = File(path)
    }
}

apply(from = file("./node_modules/@react-native-community/cli-platform-android/native_modules.gradle"));
val applyNativeModulesSettingsGradle = extra["applyNativeModulesSettingsGradle"] as groovy.lang.Closure<*>
applyNativeModulesSettingsGradle(settings)


includeBuild("dependeasy")
includeBuild("./node_modules/@react-native/gradle-plugin")

// Layer 0
include("core", "modules/core")

// Layer 1
include("lib-navigation", "modules/lib-navigation")
include("lib-mobile", "modules/lib-mobile")
include("lib-design", "modules/lib-design")

// Layer 2
include("lib-auth", "modules/lib-auth")
include("lib-media", "modules/lib-media")
include("lib-chat", "modules/lib-chat")
include("lib-ai", "modules/lib-ai")

// Pods
include("pod-feed", "modules/pod-feed")
include("pod-user", "modules/pod-user")


// Apps
include("server", "platforms/server")
include("android", "platforms/android")
include("web", "platforms/web")
include("react-native-safe-area-context", "node_modules/react-native-safe-area-context/android")

// Experiments
include("pragati", "experiments/pragati/android")
