package app.mehmaan.core.framework

enum class PlatformType {
    ANDROID, DARWIN, JS, JVM
}
// JS can be Browser/Worker
// JVM can be Desktop/Server

internal expect val __PLATFORM: PlatformType
object Platform {
    val name = __PLATFORM
}