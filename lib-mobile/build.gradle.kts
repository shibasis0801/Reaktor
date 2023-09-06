import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.darwin.*
import dev.shibasis.dependeasy.web.*

plugins {
    id("com.android.library")
    id("dev.shibasis.dependeasy.plugin")
    id("org.jetbrains.compose")
}

kotlin {
    val (commonMain, commonTest) = common {
        dependencies = {
            api(project(":core"))
            api(project(":lib-navigation"))
            api(project(":lib-auth"))
            basic()
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
//            implementation("androidx.compose.material3:material3-icons-extended:1.4.3")
        }
    }
    droid(commonMain) {
        dependencies = {
            basicAndroid()
            androidCompose(project)
            api("com.facebook.react:react-native:0.72.3") {
                exclude(module = "fbjni-java-only")
            }
            implementation("com.facebook.fbjni:fbjni:0.4.0")
        }
    }
    darwin(commonMain) {
        podDependencies = {
            framework {
                isStatic = true
            }
        }
    }
    web(commonMain)
}

android {
    defaults("app.mehmaan.mobile", file("../cpp/CMakeLists.txt"))
}
