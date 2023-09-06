package app.mehmaan.mobile.mehmaan

import app.mehmaan.mobile.jsi.ReaktorModule
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

enum class ColorScheme {
    Light,
    Dark
}
open class MehmaanConfig: ReaktorModule {
    override val name = "MehmaanConfig"
    protected val themes = mutableMapOf<ColorScheme, Theme>()
    init {
        themes[ColorScheme.Light] = lightTheme
        themes[ColorScheme.Dark] = darkTheme
    }
    fun getTheme(): Theme {
        return themes[ColorScheme.Light]!!
    }

    fun getTheme(colorScheme: ColorScheme): Theme {
        return themes[colorScheme]!!
    }

    // WIP TODO REACTDESCRIPTORS
    fun reactDescriptors() = mapOf(
        "getThemeString" to mapOf(
            "returnType" to "String",
            "parameters" to  arrayOf(
                "arg1" to "string"
            )
        )
    )

    // Should be removed in favour of just using the theme object
    fun getThemeString(): String {
        return Json.encodeToString(getTheme());
    }
}
