import com.android.build.api.variant.FilterConfiguration.FilterType.ABI
import dev.shibasis.dependeasy.Version
import dev.shibasis.dependeasy.android.defaults
import dev.shibasis.dependeasy.common.androidKoin
import groovy.lang.Closure

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dev.shibasis.dependeasy.plugin")
    id("com.facebook.react")
}
//
react {
    root.set(File("../../"))
    cliFile.set(File("../../node_modules/react-native/cli.js"))
    bundleCommand.set("ram-bundle")
    entryFile.set(File("../react/index.js"))
}


android {
    defaults("app.mehmaan.android")
    kotlinOptions { defaults() }

    splits {
        abi {
            reset()
            isEnable = true
            isUniversalApk = false
            include(*Version.architectures.toTypedArray())
        }
    }

    // For each APK output variant, override versionCode with a combination of
    // abiCodes * 1000 + variant.versionCode. In this example, variant.versionCode
    // is equal to defaultConfig.versionCode. If you configure product flavors that
    // define their own versionCode, variant.versionCode uses that value instead.
    androidComponents {
        onVariants { variant ->
            // For each separate APK per architecture, set a unique version code as described here:
            // https://developer.android.com/studio/build/configure-apk-splits.html
            // Example: versionCode 1 will generate 1001 for armeabi-v7a, 1002 for x86, etc.

            // Assigns a different version code for each output APK
            // other than the universal APK.
            variant.outputs.forEach { output ->
                val name = output.filters.find { it.filterType == ABI }?.identifier
                val baseAbiCode = Version.architectures.indexOf(name)
                // We want universal APKs to have the lowest version code, so for non-mapped ABIs don't do anything
                if (baseAbiCode != -1) {
                    // Assigns the new version code to output.versionCode, which changes the version code
                    // for only the output APK, not for the variant itself.
                    output.versionCode.set((baseAbiCode + 1) * 1000 + (output.versionCode.get() ?: 0))
                }
            }
        }
    }
}

dependencies {
    api(project(":core"))
    api(project(":lib-mobile"))
    api(project(":lib-media"))
    api(project(":pod-feed"))

    api("com.facebook.react:react-native:0.68.5") {
        exclude(module = "fbjni-java-only")
    }
    implementation(project(":react-native-safe-area-context"))  {
        exclude(module = "fbjni-java-only")
    }
    implementation("com.facebook.fbjni:fbjni:0.4.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("androidx.camera:camera-view:1.2.3")

    implementation("io.insert-koin:koin-android:${Version.Koin}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")

//    flipper()

    implementation("com.facebook.react:hermes-android")
}

apply(from = "../../node_modules/react-native-vector-icons/fonts.gradle")

apply(from = file("../../node_modules/@react-native-community/cli-platform-android/native_modules.gradle"));
val applyNativeModulesAppBuildGradle = extra["applyNativeModulesAppBuildGradle"] as Closure<*>
applyNativeModulesAppBuildGradle(project)
