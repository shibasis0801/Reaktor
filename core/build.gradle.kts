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
}

kotlin {
    val (commonMain, commonTest) = common {
        dependencies = {
            commonNetworking()
            commonCoroutines()
            commonSerialization()
            commonKoin()
//            implementation("com.squareup.okio:okio:3.5.0")
        }

        testDependencies = {
            api(kotlin("test"))
        }
    }

    web(commonMain) {
        dependencies = {
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
            basic()
//                flipper()
            androidCompose(project)
            androidKoin()
            androidCoroutines()
            lifecycle()
            networking()
            workManager()
            extensions()
            camera()
            firebase(project)
            machineLearning()
            drive()
        }
    }

    darwin(commonMain) {
        dependencies = {
            implementation("io.ktor:ktor-client-darwin:${Version.Ktor}")
        }
    }
    server(commonMain) {
        targetModifier = {
            jvmToolchain(11)
        }
        dependencies = {
            vertx()
            serverNetworking()
            implementation("com.google.firebase:firebase-admin:9.2.0")
        }
    }
}

android {
    defaults("app.mehmaan.core")
}
