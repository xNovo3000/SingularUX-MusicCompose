plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose)
}

android {

    namespace = "org.singularux.music"
    compileSdk = 36
    buildToolsVersion = "36.0.0"

    defaultConfig {
        applicationId = "org.singularux.music"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {
    // Library
    api(project(":core:permission"))
    api(project(":core:ui"))
    api(project(":data:library"))
    // AndroidX
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation)
    // Coil
    implementation(libs.coil.compose)
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    // Compose - Material 3
    implementation(libs.androidx.compose.material3)
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // Kotlin
    implementation(libs.kotlinx.serialization.json)
    // JDK Desugaring
    coreLibraryDesugaring(libs.jdk.desugar)
}