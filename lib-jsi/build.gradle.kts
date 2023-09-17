import dev.shibasis.dependeasy.web.*
import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.server.*
import dev.shibasis.dependeasy.darwin.*
import dev.shibasis.dependeasy.*

plugins {
    id("com.android.library")
    id("dev.shibasis.dependeasy.plugin")
}

kotlin {
    val (commonMain, commonTest) = common {
        dependencies = {
            api(project(":core"))
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
    defaults("dev.reaktor.jsi")
}