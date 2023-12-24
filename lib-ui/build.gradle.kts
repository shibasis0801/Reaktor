import dev.shibasis.dependeasy.web.*
import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.server.*
import dev.shibasis.dependeasy.darwin.*
import dev.shibasis.dependeasy.*

plugins {
    id("com.android.library")
    id("dev.shibasis.dependeasy.plugin")
    id("org.jetbrains.compose")
}

kotlin {
    val (commonMain, commonTest) = common {
        dependencies = {
            api(project(":core"))
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            api(compose.components.resources)
            // Needs upgrade for wasm
            api("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
        }
    }

    web(commonMain) {
        dependencies = {

        }
    }

    droid(commonMain) {
        dependencies = {

        }
    }

    darwin(commonMain) {
        dependencies = {

        }
    }
}

android {
    defaults("app.mehmaan.ui")
}