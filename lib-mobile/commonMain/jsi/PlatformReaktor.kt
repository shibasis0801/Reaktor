package app.mehmaan.mobile.jsi

@Suppress("KotlinJniMissingFunction")
interface PlatformReaktor {
    fun addModule(module: ReaktorModule, name: String = module.name)
}