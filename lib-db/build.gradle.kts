import dev.shibasis.dependeasy.web.*
import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.server.*
import dev.shibasis.dependeasy.darwin.*
import dev.shibasis.dependeasy.*

plugins {
    id("com.android.library")
    id("app.cash.sqldelight")
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
            api("app.cash.sqldelight:web-worker-driver:2.0.0")
            implementation(npm("sql.js", "1.6.2"))
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
        }
    }

    droid(commonMain) {
        dependencies = {
            api("app.cash.sqldelight:android-driver:2.0.0")
        }
    }

    darwin(commonMain) {
        dependencies = {
            api("app.cash.sqldelight:native-driver:2.0.0")
        }
    }
}

android {
    defaults("app.mehmaan.db")
}

sqldelight {
    databases {
        create("NetworkDatabase") {
            packageName.set("app.mehmaan.db")
            generateAsync.set(true)
            srcDirs("sqlMain")
        }
    }
}

