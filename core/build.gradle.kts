import dev.shibasis.dependeasy.web.*
import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.server.*
import dev.shibasis.dependeasy.darwin.*
import dev.shibasis.dependeasy.*

plugins {
    id("com.android.library")
    id("dev.shibasis.dependeasy.plugin")
    id("com.google.firebase.crashlytics")
    id("com.google.devtools.ksp")
    id("app.cash.sqldelight")
    id("org.jetbrains.compose")

}

kotlin {
    val (commonMain, commonTest) = common {
        dependencies = {
            commonNetworking()
            commonCoroutines()
            commonSerialization()
            api("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
        }

        testDependencies = {
            api(kotlin("test"))
        }
    }

    web(commonMain) {
        dependencies = {
            implementation("app.cash.sqldelight:web-worker-driver:2.0.0")
            implementation(npm("sql.js", "1.6.2"))
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            webBasic()
            react()
            webNetworking()
            webCoroutines()
            api(npm("react-native-paper", "5.8.0"))
        }
    }

    droid(commonMain) {
        targetModifier = {

        }
        dependencies = {
            implementation("app.cash.sqldelight:android-driver:2.0.0")
            basic()
//                flipper()
            androidCompose(project)
            androidCoroutines()
            lifecycle()
            networking()
            workManager()
            extensions()
            camera()
            firebase(project)
            machineLearning()
        }
    }

    darwin(commonMain) {
        dependencies = {
            implementation("app.cash.sqldelight:native-driver:2.0.0")
            implementation("io.ktor:ktor-client-darwin:${Version.Ktor}")
        }
    }
    server(commonMain) {
        targetModifier = {
            jvmToolchain(11)
        }
        dependencies = {
            implementation("app.cash.sqldelight:sqlite-driver:2.0.0")
            vertx()
            serverNetworking()
            implementation("com.google.firebase:firebase-admin:9.2.0")
        }
    }
}

android {
    defaults("dev.reaktor.core")
}

dependencies {
    add("kspCommonMainMetadata", project(":generator"))
//    add("kspJvm", project(":generator"))
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("dev.reaktor.core")
        }
    }
}