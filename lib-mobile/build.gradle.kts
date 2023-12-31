import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.darwin.*
import dev.shibasis.dependeasy.web.*

plugins {
    id("com.android.library")
    id("dev.shibasis.dependeasy.plugin")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp")
}

val cppRoot = "../cpp"
kotlin {
    val (commonMain, commonTest) = common {
        dependencies = {
            api(project(":core"))
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
//            pod("FirebaseAnalytics", "10.19.0")
//            pod("FirebaseCore", "10.19.0")
//            pod("FirebaseCrashlytics", "10.19.0")
//            pod("FirebaseMessaging", "10.19.0")
        }
        cinterops = {
            val reaktor by creating {
                defFile(file("$cppRoot/reaktor.def"))
                headers("$cppRoot/Reaktor.h")
                extraOpts("-Xsource-compiler-option", "-std=c++20")
                extraOpts("-Xcompile-source", "$cppRoot/Reaktor.cpp")
            }
        }
    }
    web(commonMain)
}

android {
    defaults("dev.reaktor.mobile", file("$cppRoot/CMakeLists.txt"))
}


dependencies {
//    configurations.forEach { conf ->
//        if (conf.name.startsWith("ksp"))
//            add(conf.name, project(":generator"))
//    }
}
